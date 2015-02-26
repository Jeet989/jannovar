package de.charite.compbio.jannovar.pedigree;

import java.util.List;

import com.google.common.collect.ImmutableList;

import de.charite.compbio.jannovar.Immutable;

/**
 * Wrapper for a immutable lists of {@link Genotype} calls for one {@link TranscriptInfo}, one list of calls for each
 * individual.
 *
 * This name list is used for ensuring that the same order and number of individuals is used in the genotype file as in
 * the pedigree file.
 *
 * @author Manuel Holtgrewe <manuel.holtgrewe@charite.de>
 */
@Immutable
public final class GenotypeList {

	/** the name of the gene for this genotype call list */
	public final String geneName;

	/** the list of individual names */
	public final ImmutableList<String> names;

	/** the lists of genotype calls, each contains one entry for each individual */
	public final ImmutableList<ImmutableList<Genotype>> calls;

	public GenotypeList(String geneID, List<String> names, ImmutableList<ImmutableList<Genotype>> calls) {
		this.geneName = geneID;
		this.names = ImmutableList.copyOf(names);
		this.calls = calls;
	}

	/**
	 * Check whether the {@link #names} of this GenotypeList are the same as the names of the members of
	 * <code>pedigree</code>.
	 *
	 * For this, the order of the names has to be the same as the number of the names. This check is important for the
	 * {@link PedigreeDiseaseCompatibilityDecorator}, where the names in the pedigree must be the same as the names in
	 * the genotype list.
	 *
	 * @return <code>true</code> if the list of {@link #names} is the same as the names of the members of
	 *         <code>pedigree</code>
	 */
	public boolean namesEqual(Pedigree pedigree) {
		return (pedigree.getNames().equals(names));
	}

	@Override
	public String toString() {
		return "GenotypeList(" + calls + ")";
	}

}
