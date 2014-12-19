package jannovar.parse;

import jannovar.exception.KGParseException;
import jannovar.io.ReferenceDictionary;
import jannovar.io.ReferenceDictionaryBuilder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import com.google.common.collect.ImmutableList;

/**
 * Allows loading of {@link ReferenceDictParser} from UCSC and RefSeq data.
 *
 * The mapping between chromosome names and RefSeq/GeneBank IDs is done using a RefSeq "chr_accessions_*" file and the
 * chromosome information is retrieved from the UCSC chromInfo.txt.gz file.
 *
 * @author Manuel Holtgrewe <manuel.holtgrewe@charite.de>
 */
public final class ReferenceDictParser {

	/** path to the chromInfo.txt.gz file */
	private final String chromInfoPath;

	/** path to the "chr_accessions_*" file */
	private final String chrAccessionsPath;

	/**
	 * Initialize the parser with the paths to the file to parse.
	 *
	 * @param chromInfoPath
	 *            path to knownGene.txt.gz file.
	 * @param chrAccessionsPath
	 *            path to "chr_accessions_*" file.
	 */
	public ReferenceDictParser(String chromInfoPath, String chrAccessionsPath) {
		this.chromInfoPath = chromInfoPath;
		this.chrAccessionsPath = chrAccessionsPath;
	}

	/**
	 * Load accessions and chromInfo file and return resulting ReferenceDictionary.
	 *
	 * @return resulting {@link ReferenceDictionary}
	 * @throws KGParseException
	 *             on problems loading or parsing data
	 */
	public ReferenceDictionary parse() throws KGParseException {
		ReferenceDictionaryBuilder builder = new ReferenceDictionaryBuilder();

		// Process accessions file.
		ImmutableList<ImmutableList<String>> accessionLines = loadTSVFile(chrAccessionsPath);
		final int CA_CHROMOSOME = 0;
		final int CA_REFSEQ_ACCESSION = 1;
		final int CA_GENBANK_ACCESSION = 3;
		int chrID = 1; // always start at 1 to get natural mapping of chr1 <-> 1
		for (ImmutableList<String> line : accessionLines) {
			builder.putContigID(line.get(CA_CHROMOSOME), chrID); // e.g. "1", "X"
			builder.putContigName(chrID, line.get(CA_CHROMOSOME)); // e.g. 1 -> "1", 23 -> "X"
			builder.putContigID("chr" + line.get(CA_CHROMOSOME), chrID); // e.g. "chr1", "chrX", for UCSC
			builder.putContigID(line.get(CA_REFSEQ_ACCESSION), chrID); // e.g. "NC_000001.10"
			builder.putContigID(line.get(CA_GENBANK_ACCESSION), chrID); // e.g. "CM000663.1"
			chrID += 1;
		}

		// Process chromosome info file.
		ImmutableList<ImmutableList<String>> chromInfoLines = loadTSVFile(chromInfoPath);
		final int CI_CHROMOSOME = 0;
		final int CI_LENGTH = 1;
		boolean seenUnknown = false;
		for (ImmutableList<String> line : chromInfoLines) {
			Integer theID = builder.getContigID(line.get(CI_CHROMOSOME));
			if (theID == null) {
				seenUnknown = true;
				continue; // unknown
			} else if (seenUnknown)
				System.err.println("WARNING: Seeing known chromosome after unknonwn in chromInfo file");
			builder.putContigLength(theID.intValue(), Integer.parseInt(line.get(CI_LENGTH)));
		}

		return builder.build();
	}

	/**
	 * Load TSV file into a list of list of strings.
	 *
	 * @param path
	 *            path to the TSV file to load.
	 * @return the contents of <code>path</code>, split into lists of strings.
	 * @throws KGParseException
	 *             on problems parsing the data
	 */
	private ImmutableList<ImmutableList<String>> loadTSVFile(String path) throws KGParseException {
		ImmutableList.Builder<ImmutableList<String>> result = new ImmutableList.Builder<ImmutableList<String>>();
		String line;

		try {
			BufferedReader reader = getBufferedReaderFromFilePath(path, path.endsWith(".gz"));
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#"))
					continue; // skip comments
				result.add(ImmutableList.copyOf(line.split("\t")));
			}
		} catch (IOException e) {
			throw new KGParseException("Problem reading TSV file: " + e.getMessage());
		}

		return result.build();
	}

	/**
	 * Open a file handle from a gzip-compressed or uncompressed file
	 *
	 * @param path
	 *            Path to the file to be opened
	 * @param isGzip
	 *            whether or not the file is gzip-compressed
	 * @return Corresponding BufferedReader file handle.
	 * @throws IOException
	 *             on I/O errors
	 */
	private static BufferedReader getBufferedReaderFromFilePath(String path, boolean isGzip) throws IOException {
		FileInputStream fin = new FileInputStream(path);
		BufferedReader br;
		if (isGzip)
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(fin)));
		else
			br = new BufferedReader(new InputStreamReader(new DataInputStream(fin)));
		return br;
	}

}
