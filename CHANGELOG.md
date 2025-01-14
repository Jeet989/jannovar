# Jannovar Changelog

## v0.41

### jannovar-core

- Fixing problem with annotating Dragen CNV VCF (#558)

## v0.40

### jannovar-core

- Coding insertions are now annotated for splicing (#554)

### jannovar-cli

- Argument `--no-3-prime-shifting` had inverse meaning (#552).
  This has been fixed now.
- Hook `--no-3-prime-shifting` argument with the rest server.
  If shifting is enabled, `?no-3-prime-shifting` can be sent as a query param in the API request to temporarily
  disable the shifting.

## v0.39

### overall

- Moved away from split between `master` and `develop` branch to using `main` branch
- Fixing broken download links in README (#533)

### jannovar-core

- Adding support for mitochondrial genetic code (#501)
- Fix problem with building ENSEMBL (#540)
- Fix problem with HGVS-to-VCF (#539)

## v0.38

### overall

* More bumping of log4j against log4shell exploit.

## v0.37

### overall

* Adding support for parsing HGVS repeats.
* Adding support for "chr2acc" chromosome to accession mapping.
* Bumping log4j version against log4shell exploit.

## v0.36

**Highlight: Pre-Built Databases**

We now provide pre-built databases via Zenodo.
This addresses various issues that were caused by changing upstream data URLs.
See `README.md` for details.

### overall

* Switching to Github Workflows for continuous integration.
* Bumping a couple of dependencies.
* Switching to JUNIT 5.
* Pre-built databases are now available via Zenodo (see `README.md` for instructions).
  We provide shell scripts instead of using Java programs for downloading the files.

### jannovar-core

* Apply fix for (#498, PR #499 by @roland-ewald of @limbus-medtec).
  This fixes a problem with right-shifting deletions on amino acid sequences.
  See the tickes and merge request for details.
* Fixing problem with GRCh38 RefSeq annotation / projection (#524).

### jannovar-cli

* Adding command `vardb-import` for importing annotations from VCF files into H2 database files.
* Adding command `vardb-list` for listing annotation meta data from `vardb-import`.
* Adding command `vardb-annotate` for annotating VCF files from Jannovar H2 database files.

### jannovar-vardbs

* Deprecating previous content of the package.
  The deprecated classes are due to be removed in v0.36.
* Adding modules for importing VCF files into H2 database files, listing the meta data conents, and annotating VCF files.

### jannovar-hgvs

* Fixing parsing of unchanged (`=`) for nucleic acide sequences (#493).
* Fixing issue with HGVS `delins` with `del` bases but no `ins` bases (#491).

## v0.35

### jannovar-core

- Update Genotype internals to pre-calculate values.

### jannovar-cli

- Fixing `default_sources.ini` file.

## v0.34

### jannovar-cli

* disabeling ensemble for mouse (does not work with an hgnc file)
* updating broken links in download source file
* Adding faMT genomes for all refseq annotations
* bumping dependency on lombok

### jannovar-core

* Remove hard-coded chrMT renaming. Filenames for download that have a ? in their URL will be splitted and only the first part before the ? is used a file name in the download path.
* Making faMT annotation for refSeq optional
* Update RefSeq parser that does not run into null-pointer exceptions on mouse and rat genome (e.g. when no exon defines the gene name)
* Fixing issue with block substitutions (#475).
* Fixing RefSeq build to properly assign transcript model (use best match with alignment) in the case of duplicates.
* Fixing issue in projection in the case of leading gaps (has no effect on CDS position prediction).
* Adding `TranscriptModel.getTrimmedSequence()` that removes leading and trailing (unaligned in RefSeq) sequence.

### jannovar-htsjdk

* Bumping HTSJDK dependency to v2.20.3 because 2.20.0 has a bug in the VariantContextBuilder


## v0.33

### overall

* Fixing `ANN` annotation field to have `.`/unbound cardinality.
* Bumping several dependencies, including HTSJDK.

### manual

* Adding conda support in installation documentation
* Fixing broken link in quickstart

### jannovar-core

* Fixing annotation of SVs that look like sequence variants (#456).
  Interpretation is to use the sequence variant annotation code now.
  This fixes a bug with annotating latest ClinVar for GRCh38.
* Prevent `Annotation.{getPutativeImpact,getPutativeImpact}()` from returning `null` (#458).
* Correctly parsing RefSeq mitochondrial transcripts.
  Bumping the required versin to `0.33-SNAPSHOT` to higlight this (#463).

## v0.32

### overall

* Changing log4j version to 2.11.2
* Changing slf4j version to 1.7.25

### jannovar-cli

* Adding a simple REST server for annotating single variants.
    * Launch with `jannovar-cli rest-server -d data/hg19_refseq.ser -d data/hg19_ensembl.ser`
    * Then, query with `/annotate-var/refseq/hg19/chr7/140453136/A/T` or `/annotate-var/ensembl/hg19/chr7/140453136/A/T`

## v0.31

### jannovar-core

* Introducing classes for representation of gapped sequences, alignments, and position projection.
* Fixing bug in ENSEMBL transcript database generation (tx version was appended twice)
* Adding flags for "has substitutions" and "has indels" to `TranscriptModel` that get filled for RefSeq transcripts.
* Correctly parsing of RefSeq transcripts with indels.

### jannovar-cli

* Using HTTP protocol instead of FTP everywhere as it's possible (#451).
* Fixing HGVS conversion for indels (#452).

## v0.30

Bugfix release.

### jannovar-core

* Fixing interpretation of `INFO/SVTYPE`, urldecode and only use the first component after splitting at ":".
* Interpreting SV type annotation for SV2 more correctly.

## v0.29

### jannovar-core

* Putative impact of splice_region_variant has changed from MODERATE to LOW (see issue #439)
* Decreasing log verbosity in one location when building database.
* Fixing CDS region import in `RefSeqParser`
* Putative impact of `splice_region_variant has` changed from MODERATE to LOW (see issue #439)
* Fixing SV annotation using hg38/ucsc for transcripts without gene ID (see #444).
* Adding support for rn6 RefSeq transcripts.
  Adding `allowNonCodingNm` directive for data source INI file to disable check that RefSeq NM transcript has CDS.
* Adding versions to ENST accessions for ENSEMBL.

### jannovar-vardbs

* Bugfix: TSVAnnotator did not use end given column.

## v0.28

### jannovar-cli

* Bumping ENSEMBL versions for GRCh37 and GRCh38.
* Fixing sources information for updated ENSEMBL downloads.
* For ENSEMBL, use ENSEMBL-provided mapping from ENSG to HGNC ID for Entrez ID assignment.
  This is necessary as Ensembl gene IDs turn out to be not so stable between hg37 and hg38 after all.
  Case in point: `ENSG00000276141` vs. `ENSG00000187667`.
* Adding `--gene-ids` argument to downloader for creating smaller databases (mostly for test purposes).
* Adding SV support to jannovar-cli, includes tests.
* Using ENSEMBL-provided mapping from ENSG to Entrez ID in the case HGNC mapping does not work.

### jannovar-htsjdk

* Adding SV support to jannovar-htsjdk
* Bumping HTSJDK dependency to v2.18.2

### jannovar-core

* Changing upstream/downstream size to 5kbp.
* Support for prioritizing RefSeq transcript on the PAR of chrX over those of chrY
* Refactorizations to improve performace using `EnumSet`.
* Extended `VariantEffect` for the effects of structural variants.
  Removing documentation that the effect is not used in Jannovar for some now interpreted ones.
  Also variant effect for non-coding variants is added using the [current VEP predictions](https://www.ensembl.org/info/genome/variation/prediction/predicted_data.html) as a template.
* Prohibiting creating `GenomeVariant` with symbolic alleles.
  Throwing new checked exception `InvalidGenomeVariant` case of error.
* Fixing SO term ID for `VariantEffect.DISRUPTIVE_INFRAME_DELETION`
* Correctly parsing transcript version for ENSEMBL when available (not available for b75/GRCh37).
* Making transcript model building (for `download`) more memory efficient.

## v0.27

### jannovar-cli

* Integrating support for thousand genomes VCF
* Integrating thousand genomes/ExAc count limits into inheritance filter

### jannovar-vardbs

* Adding support for thousand genomes VCF

### jannovar-htsjdk

* Adding support for limiting genomes/ExAc counts into inheritance filter

## v0.26

### jannovar-cli

* Making `OneParentGtFiltered` filter optional. The default setting to `false` (specify `--one-parent-gt-filtered-filters-affected` to enable).

### jannovar-core

* **Moving variants in non-coding transcripts after UTR variants.**

### jannovar-hgvs

* Fixing parser issue for nucleotide indels (#408).

### jannovar-htsjdk

* Obey the `options.escapeAnnField` parameter for escaping the variant effect in the `ANN` field.

## v0.25

### overall

* Changing HTSJDK version to 2.14.3
* Using the one letter amino acid code in HGVS representation as default (changes in core, hgvs, htsjdk and cli). Now the cli option `--3-letter-amino-acids` works as expected.

### jannovar-cli

* Support for [RefSeq GRCh37.p13 interim release](https://www.ncbi.nlm.nih.gov/books/NBK430989/#_news_02-14-2017-interim-annotation-update-human_)
* Support of new RefSeq headers
* Using RefSeq GRCh38.p12 annotation instead of GRCh38.p7

### jannovar-vardbs

* Replacing whitespace with string when annotating from TSV file.

### jannovar-htsjdk

* Fixing bug in GenomeRegionSequenceExtraction. Error reports always sequences from the first contig in the referebnce file and not the requested contig. Affects only the cli command `hgvs-to-vcf`.

## v0.24

### jannovar-cli

* Fixing annotation with Polyphen prediction (data type)

### overall

* Changing HTSJDK version to 2.14.0
* Codestyle improvements

### jannovar-core

* Fixing mendelian "bug" #393 (has no affect because check was not necessary)
* New inheritance mode: mitochondrial
* Bugfix ProgressBar (doPrint was always true)

### jannovar-vardbs

* Fixed problem with interpretation of Clinvar annotation origin.
* Clinvar `BEST_AC` and `BEST_AF` are now named `AC_POPMAX` and `AF_POPMAX` to be consitent with gnomAD

## v0.23

### overall

* Changing Guava version to 0.22
* Changing slf4j version to 1.7.24
* Changing log4j version to 2.8.2

### jannovar-cli

* Adding experimental support for annotating with VCF files.
* Adding experimental support for annotating with tabix-indexed TSV files and dbNSFP.
* Integrating the advanced pedigree-based filters (useful for filtration to de novo variants).
* Making it possible to override database INI settings using user-specified INI files.

### jannovar-core

* Fixing stop loss annotation (#351).
* Finishing renaming of TranscriptInfo to TranscriptModel (#348).
* Upstream and downstream variant were considered "not off exome". They now are.
* Adding mitochondrial filtering function (#362).

### jannovar-filter

* Adding code for performing more advanced filtration/annotation filtering to de novo variants.
* Improving documentation of `MaxFreqAr` and `MaxFreqAd` in header.


### jannovar-vardbs

* Adding experimental support for annotating with VCF files
* Adding experimental support for annotating with tabix-indexed TSV files and dbNSFP

### jannovar-filter

* Fixing bug that ignored variant filters for recessive annotation

## v0.22

### jannovar-htsjdk

* Fixin NPE problem with inheritance annotation

### jannovar-statistics

* Also counting number of variants on contigs
* Fixing counting bug that made UTR3 variants be counted as UTR5
* Fixing NPE in case of null variant annotations (e.g., unknown contig)

### jannovar-vardbs

* Fixing a problem with normalization on variant annotation
* Fixing problem with default value of `CLNSIG` (`"25"` -> `"255"`)

### jannovar-filter

* Incorporating gnomAD annotation into exclusion by frequency for inheritance filter (#343)
* Fixing header description for `MinAafHomAlt` and `MaxAafHomRef` (#342)

### jannovar-cli

* Checking that reference is given also for gnomAD VCF annotation

## v0.21

### all

* Fixing language in mvn surfire plugin. Now mvn tests work on locale de_DE etc..

### jannovar-cli

* Adding `--interval` argument for only processing a part of the file
* Adding `statistics` command for computing statistics on variants in VCF file
* Fixing bug in HGVS to VCF
* Better handling missing `.dict` file for HGVS to VCF translation
* Adding `--annotate-as-singleton-pedigree` parameter for annotation of singleton pedigrees without pedigree file (single individual is assumed to be affected)
* More friendly user message in case of unsorted files on inheritance mode annotation
* Interpretation of filters in compatible inheritance mode annotation
* Integrating new jannovar-filter into Jannovar CLI.
  Filtered genotypes will be passed into the inheritance filter as no-call.
* Adding annotation with ClinVar
* Printing warnings next to the annotations in `annotate-pos`
* AR inheritance annotation of two siblings bugfix (no parents avaiable in comp.het mode) #314

### jannovar-filter

* Adding functionality to add filters based on frequencies found in dbSNP and ExAC
* Adding back as module for threshold-based filtration.
  This module allows to create genotype-wise soft-filters for low coverage.
  Also, variants can be soft-filtered based on whether the genotype calls of all affected individuals are filtered out.

### jannovar-core

* Extending API to expose mendelian checks for comp het./ad alt (via `SubModuleOfInheritance` and `MendelianInheritanceChecker`
* Jannovar version is now written out to database file which allows better error checks and compatibility messages
* Un-deprecating `BestAnnotationListTextGenerator` and `AllAnnotationListTextGenerator` classes, useful for text-based output formats
* Changing behaviour of `VariantEffect.isOffExome()` and adding a variant that allows to decide between UTR on/off exome and non-consensus splice region on/off exome
* Making the behaviour of overriding transcripts configurable at least in the code, using default to not do this any more
* Adding `WARNING_REF_DOES_NOT_MATCH_TRANSCRIPT` to `AnnotationMessage`
* Properly pushing through warnings from the annotators into the returned `VariantAnnotation` object
* Pedigree files are now more compatible to the PLINK format
	* whitespace separated instead of tab separated (read only, written as TSV)
	* interpreting any value not in {1, 2} to be "unknown" sex instead (coded as 0) of throwing

### jannovar-htsjdk

* Fixing bug in transcript-to-genome translation, in HGVS the stop codon is not part of the CDS but in `TranscriptModel` it is
* Optional interpretation of certain filters in GeneWiseMendelianAnnotationProcessor.
* Extending interface of `VariantContextAnnotator` for automatic error annotation generation, previously in jannovar-cli
* Adding `VariantEffectHeaderExtender` class to `jannovar-htsjdk`
* Fixing bug with problems of unmodifiable Attributes (error annotation).

### jannovar-vardbs

* Also writing out variant allele origin for dbSNP
* Adding annotation with COSMIC
* Fixing header description for exac database
* Fixing output of `DBSNP_CAF` to also contain reference allele AF
* Adding annotation with ClinVar, can annotate all clinvar variants

### jannovar-inheritance-checker

* Removing this outdated module.
  Use the classes in `de.charite.compbio.jannovar.mendel` instead

### jannovar-stats

* all-new module for gathering statistics on VCF files

## v0.20

### all

* Change email/organisations in master pom

### jannovar-core

* `GenotypeCalls.getGenotypeForSample()` returns a "no-call" genotype now instead of `null`

### jannovar-htsjdk

* fix to annotation with compatible mode of inheritance (#289)
* update to htsjdk 2.8.1

### jannovar-cli

* removing requirement for proper contig `contig` lines in gene-wise gene annotation
* fixing NPE in the case of no `contig` lines
* improving error message on samples in VCF file that are not in pedigree
* fix to annotation with compatible mode of inheritance (#289)
* better overview on CLI help message
* if ref-fasta is not set properly a nicer error message will be shown.

### jannovar-vardbs

* Fixing bug with problems of unmodifieable Attributes.
* Including Hom/Het/Hemi counds of ExAC (#295)
* update to htsjdk 2.8.1

## v0.19

This is a bugfix release.

### manual

* Manual loads version from central POM file now
* Adjusting manual links to point to `javadoc.io`

### jannovar-core

* Fixing integration of HGNC into the downloaded databases
    * For UCSC, HGNC records are searched based on the Entrez ID.
      If HGNC does not know the Entrez then only the Entrez ID from UCSC is written as additional ID.
    * For RefSeq, linking is done through Entrez ID.
      If HGNC does not know the Entrez then only the Entrez ID from RefSeq is written as additional ID.
    * For ENSEMBL linking is done through the ENSEMBL gene id.
      If this is not known to HGNC then no additional IDs are annotated.
* Fixing problem with `UnsupportedOperationException` in `jannovar-htsjdk`

## v0.18

### all

* replace charite email of p. robinson with the new one of jax

### jannovar-cli

* Renaming `tx-to-chrom` to `hgvs-to-vcf`, also in Java module names.
* CLI changes such that one VCF input and one VCF output path can be used only
* Replacing apache commons-cli with argparse4j for a more modern and usable CLI
* Consistently writing out HUGO symbols for gene names, using the `hgnc_complete_set.txt` information downloaded when building the annotation DB
* Upgrading from ENSEMBL-74 to ENSEMBL-75 for annotation database files
* Removing support for old Jannovar-style annotations (#241)
* Adding new command for annotating csv files (annotate-csv)

### jannovar-htsjdk

* Properly annotating Mendelian inheritance for intergenic variants

### jannovar-core

* downloading `hgnc_complete_set.txt` together with data sets, `TranscriptModel` objects now consistently contain additional IDs
* making ENSEMBL parsing more robust (falling back to transcript name if no transcript ID)
* fixing bug #248 for ENSEMBL that used `gene_id` for `gene_name`
* bugfix of NullPointerException in RefSeqParser while parsing refSeq curated
* bugfix space in SeqOID of SYNONYMOUS_VARIANT
* Update link to HGVS Nomenclature
* Now BestAnnotationListTextGenerator shows really the best and not all annotations!

### Manual

* Documenting cli changes
* Adding additional sites contributing, FAQ and how to filter
* Better description of installations and quickstart


## v0.17

### jped-cli

* this is gone, the functionality is now available as part of jannovar-cli

### jannovar-filter

* this module is done, everything here is merged into jannovar-htsjdk

### jannovar-vardbs

* The first version ships with support for dbSNP b147, ExAC 0.3, and the UK10K COHORT data base
* Initial version of this module, the aim is precise annotation from variant databases

### jannovar-cli

* Updated `default_sources.ini` for latest patches of mouse and human genomes
* Using one-letter amino acid code by default
* Removed slf4j2 warning at program startup
* Checking pedigree for compatibility with VCF file if given

### jannovar-core

* Adjusting API for annotating amino acid code by default
* Checking pedigree for compatibility with genotypes on Mendelian inheritance checking
* Refurbishing `Genotype`, `GenotypeList`, and `GenotypeListBuilder` in `de.charite.compbio.jannovar.mendel`.
* Moving `ModeOfInheritance` to `de.charite.compbio.jannovar.mendel`.
* Creating new package `de.charite.compbio.jannovar.mendel` with code for filtering for mendelian inheritance modes.
* Renaming of `ModeOfInheritance.UNINITIALIZED` to `ModeOfInheritance.ANY`.
* Fixing handling of invalid transcripts (e.g., incomplete 3' end)
* Adding `altGeneIDs` mapping to `TranscriptModel`, makes data bases backwards incompatible.
* Rewrite of GFF parsers for RefSeq and ENSEMBL.
* Bumping HTSJDK to 2.5.0, requiring Java 8 from now on.
* Removal of `AnnotationCollector`, priotization of variant effects is done after collecting all effect predictions now.
* Fix for intronic variants between 5' or 3' UTRs. These variants were misclassified as `FIVE_PRIME_UTR_VARIANT` or `THREE_PRIME_UTR_VARIANT`. SequenceOntology implements new terms so that we can decide between the two UTR exon and intron variants. Now we have `FIVE_PRIME_UTR_EXON_VARIANT` or `FIVE_PRIME_UTR_EXON_INTRON_VARIANT` (the same for `THREE_PRIME_UTR_EXON_VARIANT` or `THREE_PRIME_UTR_EXON_INTRON_VARIANT`).

### jannovar-cli

* Adding better progress display with estimate of pending time.
* Adding support for annotating values from dbSNP VCF file (currently, only b147 is supported).
* Adding simple progress reporting (from verbosity level 2).
* Using Java 8 stream interface for `VariantContext` processing.
* Removing support for Jannovar output format, VCF offer all features and more.

## v0.16

### jannovar-cli

* Updating htsjdk to 1.142
* using simple logger of slf4j
* fixing version output in command line help
* changing command line interface to use more named arguments
* removing deprecated usage of commons-cli command line parser
* renaming of some internal classes and functions, fixing Javadocs

### jannovar-core

* fixing bug in `TranscriptSequenceChangeHelper` for reverse transcript (did not reverse complement alternate allele)
* fixing bug in parsing GFF3 with some transcripts (e.g. GNAT1)
* less intrusive escaping in `ANN` field
* renaming of some internal classes and functions, fixing Javadocs

### jannovar-htsjdk

* Updating htsjdk to 1.142
* renaming `InvalidGenomeChange` to `InvalidGenomeVariant`
* renaming `VariantContextAnnotator.buildGenomeChange` to `.buildGenomeVariant`
* renaming of some internal classes and functions, fixing Javadocs

### jannovar-hgvs

* extending API of ProteinChange hierarchy for HGVS generation
* renaming of some internal classes and functions, fixing Javadocs

### jped-cli

* Updating htsjdk to 1.142
* changing command line interface to use more named arguments

### jannovar-inheritance-checker

* adding two new functions to InheritanceCompatibilityChecker
* resolve boolean if passes inheritance into set where passed inheritances are stored
* Updating htsjdk to 1.142

### manual

* updating manual for 0.16 and using parameters for commands!
* updating readme for parameters

## v0.15

### jannovar-core

* making `CompatibilityCheckerAutosomalRecessiveHomozygous` public
* using jannovar-hgvs for representing the changes
* more precise HGVS annotation in some cases
* predictions are wrapped in parentheses
* Mark everything that is related to the compatibility checkers as depricated (see new jannovar-inheritance-checker)

### jannover-hgvs

* adding module for parsing and representing HGVS-compatible nucleic and protein changes


### jannover-htsjdk

* Updating htsjdk to 1.138
* Replacing depricatded method `VariantContext.getChr()` with `VariantContext.getContig()`

### jannovar-cli

* Updating htsjdk to 1.138
* Replacing depricatded method `VariantContext.getChr()` with `VariantContext.getContig()`
* Updating commons-cli to 1.3.1

### jannover-inheritance-checker

* Bugfix detecting autosomal chromosomes
* Bugfix with handling variant files with a leading "chr" in the contig.
* Adding this new module.
* Replaces the compatibility checker oh jannobvar-core.
* Now runs with VariantContext (htsjdk) instead of Jannovar Genotypes
* Use `InheritanceCompatibilityChecker.Builder` to build `InheritanceCompatibilityChecker`.
* Use the method `getCompatibleWith` of the `InheritanceCompatibilityChecker` with a List of `VariantContext`.
* The method will return all `VariantContext` that matches the inheritance. If no variant matches the List is empty.

### jannover-filter

* Refactoring `VariantWiseInheritanceFilter` to handle the new `InheritanceCompatibilityChecker`.
* Rewrite `GeneWiseInheritanceFilter` to handle the new `InheritanceCompatibilityChecker`.
* Updating htsjdk to 1.138
* Replacing depricatded method `VariantContext.getChr()` with `VariantContext.getContig()`

### jped-cli

* Adapting program to the `GeneWiseInheritanceFilter` and `VariantWiseInheritanceFilter` (see jannovar-filter)
* Updating commons-cli to 1.3.1
* Changing cli option inheritance-mode to multiple args (Now you can check multiple inheritances at once)

## v0.14

### jannovar-cli

* Improving output file generation, jannovar-cli now uses the same extension
  as in the input and the infix is configurable instead of being fixed to
  ".jv".
* Default extension is ".vcf.gz" instead of ".vcf" now.

### jannovar-core

* Fixing label for `FRAMESHIFT_VARIANT` in `VariantEffect`.
* Moving CompatibilityCheckerException to package
  `...jannovar.pedigree.compatibilitychecker`
* Fixing bug in transcript coordinate projection.
* Renaming `TranscriptSequenceChangeHelper.getCDSWithChange` to
  `.getCDSWithGenomeVariant`.
* Renaming `*.getChange()` to `*.getGenomeVariant()`
* Renaming `VariantAnnotator.buildAnnotationList` to `.buildAnnotations`,
  `VariantContextAnnotator.buildAnnotationList` to `.buildAnnotations`,
  and `VariantContextAnnotator.buildErrorAnnotationList` to
  `VariantContextAnnotator.buildErrorAnnotations`
* VariantAnnotations does not implement `List<Annotation>` any more
* Adding `VariantAnnotations.getAnnotations`
* Renaming `AnnotationList` to `VariantAnnotations`
* changing treatment of insertions at exon/intron junctions; they are
  considered as intronic insertions now that affect splicing
* converting `GenomeVariant` of `AnnotationList` to always be on the forward
  strand after construction of  `AnnotationList`
* deprecating the `{,All,Best}AnnotationTextGenerator` classes

## v.0.13

### jannovar-cli

* Moving `JannovarOptions` into jannovar-cli.
* Displaying online help on unknown Jannovar command.
* Fixing `NullPointerException` bug for local paths.
* Switching to official HTSJDK release and version 0.128.
* Writing out annotation about Jannovar call and version into the VCF file.
* Added option `--no-3-prime-shifting` to disable shifting towards the
  3' end of the transcripts.
* Added option `--no-escape-ann-field` to disable escaping of the `ANN`
  `INFO` field.
* Variants in `ANN` field are now annotated with proper Sequence Ontology
  terms.

### jannovar-htsjdk

* Modified `VariantContextWriterConstructionHelper` to allow explicit
  disabling of index creation.
* Modified `VariantContextAnnotator` for adjustment to the new Exomiser.
* Switching to official HTSJDK release and version 0.128.
* Changing `VariantContextWriterConstructionHelper` to allow writing out
  of additional header lines.
* Added option to `VariantContextAnnotator#Options` for disabling
  3' shifting.
* Modified `VariantContextAnnotator` allowing to disable escaping of the
  `ANN` `INFO` field.

### jannovar-core

* Moving `JannovarOptions` into jannovar-cli.
* Renaming `ACompatibilityChecker` and `ICompatibilityChecker`.
* Adding `GenomePosition.differenceTo(GenomeInterval)`.
* Renaming package `de.charite.compbio.jannovar.io` to `de.charite.compbio.jannovar.data`
* Renaming `AnnotationLocation.toHGVSString` to `.toHGVSChunk`.
* Adding `Pedigree.subsetOfMembers`
* Renaming `GenomeChange` to `GenomeVariant`, same with types having the same
  prefix.
* Introducing `DatasourceOptions` for configuring data download.
* Removing support for using `"-"` as REF or ALT value.
* Making previous `public final` members `private final` (or
 `protected final`) and adding getters for read-only access to them.
* Removing position type member of `CDSInterval`.
* Using type `Strand` instead of `'+'` and `'-'`, requires database rebuild.
* Adding enum `Strand` with `PLUS` and `MINUS` values.
* Adding `VariantEffect.isOffExome` and updating
  `VariantEffect.isOffTranscript`.
* Removing `genomeRegion` member from `GenotypeList`. Also, adjusting the
  pedigree compatibility checkers for this, the check for being on the X
  chromosome has to be performed outside the checker now.
* `VariantList.getHighestImpactEffect` now returns
  `VariantEffect#SEQUENCE_VARIANT` if no annotation can be found.
* `VariantList` implements the `List<Annotation>` interface now and the
  `entries` member has become private.
* Adding `VariantEffect#SEQUENCE_VARIANT` for variants with unknown
  effects.
* `GenomeChange.toString()` now always converts to forward strand.
* Fixing bug in `Annotation` and enforcing forward strand `GenomeChange`
  instances.
* Updates to the manual.
* `JannovarData` now also stores a mapping from transcript accession to
  `TranscriptModel` and from gene symbol to `TranscriptModel`.
* Adding functionality for conversion from CDS to transcript and genome
  position and tests.
* Adding `AnnotationBuilderOption` object that allows disabling of 3'
  shifting towards the transcript.
* Adding `JannovarOptions#escapeAnnField`.
* Renaming `VariantType` to `VariantEffect`
* Changing `VariantType` to use proper Sequence Ontology terms. Legacy
  names can be obtained through `VariantType#getLegacyName`.
* Spliting `CompatibilityCheckerXRecessive` into `CompatibilityCheckerXRecessiveCompoundHet` and `CompatibilityCheckerXRecessiveHomozygous`. Now all inheritance checkers ar ready to use (AR,XR,AD,XD)
* move all pedigree compatibility checkers from `de.charite.compbio.jannovar.pedigree` to `de.charite.compbio.jannovar.pedigree.compatibilitychecker` and divide it into ar,xr,ad,xd.
* generate interface `ICompatibilityChecker` for pedigree compatibility checkers.
* Combine compatibility fields and methods in an abstract class`ACompatibilityChecker` to unify methods, builders, and fields.

### jannovar-filter

* Splitting into `jped-cli` and `jannovar-filter`
* Changing public final members to accessors.
* `jannovar-filter` now has the Jannovar DB as the mandatory first argument.

### jannovar-htsjdk

* Changing public final members to accessors.

## v0.12

### jannovar-htsjdk

* Started bridge module between Jannovar and HTSJDK.

### jannovar-filter

* Started tool for mode of inheritance--based filters.

### jannovar-cli

* Splitting out bridge module between jannovar-core and HTSJDK to
  jannovar-htsjdk.
* Adding implementation of variant annotation standard 1.0.
* Adding unit tests for jannovar-cli.
* Fixing problem with empty `INFO` fields in output.
* Adding back `--output-dir` to jannovar-cli.
* Writing output parallel to input file by default.
* Adding `-v` and `-vv` command line options.
* Fixing problems with block substitution (delins) case (#87).

### jannovar-core

* Adding initial support for the transcript support level feature of the new VCF
  annotation standard (only in very recent ENSEMBL releases, apparently).
* `TranscriptModel#geneID` is now a `String`
* Update in various classes, e.g. Annotation.
* Fixing bug in PED parsing (empty lines are properly skipped now).
* More tests and fixes for the inheritance compatibility checkers.
* Updating `Annotation` for the variant annotation standard.
* `TranscriptPosition` and `TranscriptInterval` use zero-based positions now.
* Reordering values of `VariantType`.
* Somewhat renaming `VariantType` method names.
* Removing the `VariantType#size` function in favor of a `static public`
  `final` member.
* Using log4j/slf4j for I/O in jannovar-core.
* Adding `PrintStream` as parameter to `JannovarOptions#print`.
* Compressing serialized file.
* Changing namespace to `de.charite.compbio.jannovar`.
* Making `VariantType#priorityLevel` a non-static member.
* Renaming `TranscriptInfo` to `TranscriptModel`.
* Moving `HG19RefDictbuilder` from tests to main.
* Using `ImmutableMap` in `Translator` for small performance improvements.
* Using `StringBuilder`-based concatenation of strings for generation of HGVS
  strings etc. since this is much faster than using `String#format`.
* `GenomePosition` and `GenomeInterval` use zero-based coordinates internally
  now.

## v0.11
