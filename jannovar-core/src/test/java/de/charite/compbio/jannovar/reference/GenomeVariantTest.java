package de.charite.compbio.jannovar.reference;

import de.charite.compbio.jannovar.annotation.InvalidGenomeVariant;
import de.charite.compbio.jannovar.data.ReferenceDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenomeVariantTest {

	/**
	 * this test uses this static hg19 reference dictionary
	 */
	static final ReferenceDictionary refDict = HG19RefDictBuilder.build();

	GenomePosition genomePosOneBasedForward;
	GenomePosition genomePosZeroBasedForward;
	GenomePosition genomePosZeroBasedReverse;

	@BeforeEach
	public void setUp() {
		this.genomePosOneBasedForward = new GenomePosition(refDict, Strand.FWD, 1, 123, PositionType.ONE_BASED);
		this.genomePosZeroBasedForward = new GenomePosition(refDict, Strand.FWD, 1, 122, PositionType.ZERO_BASED);
		this.genomePosZeroBasedReverse = new GenomePosition(refDict, Strand.REV, 1, 122, PositionType.ZERO_BASED);
	}

	@Test
	public void testConstructorNoUpdate() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "A", "C");
		Assertions.assertEquals(this.genomePosOneBasedForward, change.getGenomePos());
		Assertions.assertEquals("A", change.getRef());
		Assertions.assertEquals("C", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandZeroRefBasesOneBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "", "C", Strand.REV);
		Assertions.assertEquals(this.genomePosOneBasedForward.shifted(-1).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandOneRefBaseOneBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "A", "C", Strand.REV);
		Assertions.assertEquals(this.genomePosOneBasedForward.shifted(0).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("T", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandThreeRefBasesOneBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "AAA", "CCC", Strand.REV);
		Assertions.assertEquals(this.genomePosOneBasedForward.shifted(2).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("TTT", change.getRef());
		Assertions.assertEquals("GGG", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandZeroRefBasesZeroBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosZeroBasedForward, "", "C", Strand.REV);
		Assertions.assertEquals(this.genomePosZeroBasedForward.shifted(-1).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandOneRefBaseZeroBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosZeroBasedForward, "A", "C", Strand.REV);
		Assertions.assertEquals(this.genomePosZeroBasedForward.shifted(0).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("T", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testConstructorChangeStrandThreeRefBasesZeroBased() throws InvalidGenomeVariant {
		GenomeVariant change = new GenomeVariant(this.genomePosZeroBasedForward, "AAA", "CCC", Strand.REV);
		Assertions.assertEquals(this.genomePosZeroBasedForward.shifted(2).withStrand(Strand.REV), change.getGenomePos());
		Assertions.assertEquals("TTT", change.getRef());
		Assertions.assertEquals("GGG", change.getAlt());
	}

	@Test
	public void testConstructorStripLeading() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "AAA", "AAC");
		GenomePosition expectedPos = new GenomePosition(refDict, this.genomePosOneBasedForward.getStrand(),
			this.genomePosOneBasedForward.getChr(), this.genomePosOneBasedForward.getPos() + 2, PositionType.ZERO_BASED);
		Assertions.assertEquals(expectedPos, change.getGenomePos());
		Assertions.assertEquals("A", change.getRef());
		Assertions.assertEquals("C", change.getAlt());
	}

	@Test
	public void testConstructorStripTrailing() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "AGG", "CGG");
		Assertions.assertEquals(this.genomePosOneBasedForward, change.getGenomePos());
		Assertions.assertEquals("A", change.getRef());
		Assertions.assertEquals("C", change.getAlt());
	}

	@Test
	public void testConstructorStripBoth() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "GGACC", "GGCCC");
		GenomePosition expectedPos = new GenomePosition(refDict, this.genomePosOneBasedForward.getStrand(),
			this.genomePosOneBasedForward.getChr(), this.genomePosOneBasedForward.getPos() + 2, PositionType.ZERO_BASED);
		Assertions.assertEquals(expectedPos, change.getGenomePos());
		Assertions.assertEquals("A", change.getRef());
		Assertions.assertEquals("C", change.getAlt());
	}

	@Test
	public void testWithStrandZeroBases() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "", "C").withStrand(Strand.REV);
		GenomePosition expected = this.genomePosOneBasedForward.shifted(-1);
		GenomePosition actual = change.getGenomePos();
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals("", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testWithStrandOneBase() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "A", "C").withStrand(Strand.REV);
		GenomePosition expected = this.genomePosOneBasedForward.shifted(0);
		GenomePosition actual = change.getGenomePos();
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals("T", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testWithStrandTwoBases() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "AA", "C").withStrand(Strand.REV);
		GenomePosition expected = this.genomePosOneBasedForward.shifted(1);
		GenomePosition actual = change.getGenomePos();
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals("TT", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testWithStrandThreeBases() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "AAA", "C").withStrand(Strand.REV);
		GenomePosition expected = this.genomePosOneBasedForward.shifted(2);
		GenomePosition actual = change.getGenomePos();
		Assertions.assertEquals(expected, actual);
		Assertions.assertEquals("TTT", change.getRef());
		Assertions.assertEquals("G", change.getAlt());
	}

	@Test
	public void testGetGenomeIntervalForward() {
		GenomeVariant change = new GenomeVariant(this.genomePosOneBasedForward, "A", "C");
		GenomeInterval genomeInterval = change.getGenomeInterval();
		GenomeInterval expectedInterval = new GenomeInterval(refDict, Strand.FWD, 1, 123, 123, PositionType.ONE_BASED);
		Assertions.assertEquals(expectedInterval, genomeInterval);
		Assertions.assertEquals(expectedInterval, genomeInterval);
	}

	@Test
	public void testGetGenomeIntervalReverse() {
		GenomeVariant change = new GenomeVariant(this.genomePosZeroBasedReverse, "A", "C");
		GenomeInterval genomeInterval = change.getGenomeInterval();
		GenomeInterval expectedInterval = new GenomeInterval(refDict, Strand.REV, 1, 122, 123,
			PositionType.ZERO_BASED);
		Assertions.assertTrue(expectedInterval.equals(genomeInterval));
		Assertions.assertEquals(expectedInterval, genomeInterval);
	}
}
