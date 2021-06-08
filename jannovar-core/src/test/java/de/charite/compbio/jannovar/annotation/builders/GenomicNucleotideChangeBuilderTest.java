package de.charite.compbio.jannovar.annotation.builders;

import de.charite.compbio.jannovar.data.ReferenceDictionary;
import de.charite.compbio.jannovar.reference.GenomePosition;
import de.charite.compbio.jannovar.reference.GenomeVariant;
import de.charite.compbio.jannovar.reference.HG19RefDictBuilder;
import de.charite.compbio.jannovar.reference.Strand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenomicNucleotideChangeBuilderTest {

	static final ReferenceDictionary refDict = HG19RefDictBuilder.build();
	private GenomeVariant varIns;
	private GenomeVariant varDel;
	private GenomeVariant varSNV;
	private GenomeVariant varSub;
	private GenomeVariant varInv;

	@BeforeEach
	public void setUp() throws Exception {
		varIns = new GenomeVariant(new GenomePosition(refDict, Strand.FWD, 1, 100), "", "CGAT");
		varDel = new GenomeVariant(new GenomePosition(refDict, Strand.FWD, 1, 100), "CGAT", "");
		varSNV = new GenomeVariant(new GenomePosition(refDict, Strand.FWD, 1, 100), "C", "T");
		varSub = new GenomeVariant(new GenomePosition(refDict, Strand.FWD, 1, 100), "CGAT", "TTTT");
		varInv = new GenomeVariant(new GenomePosition(refDict, Strand.FWD, 1, 100), "CGAT", "ATCG");
	}

	@Test
	public void testInsertion() {
		Assertions.assertEquals("100_101insCGAT", new GenomicNucleotideChangeBuilder(varIns).build().toHGVSString());
	}

	@Test
	public void testDeletion() {
		Assertions.assertEquals("101_104delCGAT", new GenomicNucleotideChangeBuilder(varDel).build().toHGVSString());
	}

	@Test
	public void testSNV() {
		Assertions.assertEquals("101C>T", new GenomicNucleotideChangeBuilder(varSNV).build().toHGVSString());
	}

	@Test
	public void testSubstitution() {
		Assertions.assertEquals("101_103delCGAinsTTT", new GenomicNucleotideChangeBuilder(varSub).build().toHGVSString());
	}

	@Test
	public void testInversion() {
		Assertions.assertEquals("101_104inv", new GenomicNucleotideChangeBuilder(varInv).build().toHGVSString());
	}

}
