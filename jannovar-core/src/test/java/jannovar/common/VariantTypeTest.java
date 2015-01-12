package jannovar.common;

import jannovar.annotation.VariantType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the class VariantType. This class is pretty simple, but we want to avoid the possibility of an additional
 * variant type being added to this class and forgotten elsewhere. There are currently 27 constants (Sep 13, 2014),
 * e.g., DOWNSTREAM, FS_DELETION,
 */
public class VariantTypeTest {

	@Test
	public void testNumberOfConstants() {
		int n = VariantType.class.getEnumConstants().length;
		Assert.assertEquals(33, n);
	}

	@Test
	public void testAllConstantsAreInPriorityList() {
		int n = VariantType.getPrioritySortedList().length;
		int m = VariantType.class.getEnumConstants().length;
		Assert.assertEquals(n, m);
	}

	@Test
	public void testPriorityLevel1() {
		int n = VariantType.priorityLevel(VariantType.FS_DELETION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel2() {
		int n = VariantType.priorityLevel(VariantType.FS_INSERTION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel3() {
		int n = VariantType.priorityLevel(VariantType.NON_FS_SUBSTITUTION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel4() {
		int n = VariantType.priorityLevel(VariantType.FS_SUBSTITUTION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel5() {
		int n = VariantType.priorityLevel(VariantType.MISSENSE);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel6() {
		int n = VariantType.priorityLevel(VariantType.NON_FS_DELETION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel7() {
		int n = VariantType.priorityLevel(VariantType.NON_FS_INSERTION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel8() {
		int n = VariantType.priorityLevel(VariantType.SPLICE_DONOR);
		Assert.assertEquals(1, n);
		n = VariantType.priorityLevel(VariantType.SPLICE_DONOR);
		Assert.assertEquals(1, n);
		n = VariantType.priorityLevel(VariantType.SPLICE_REGION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel9() {
		int n = VariantType.priorityLevel(VariantType.STOPGAIN);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel10() {
		int n = VariantType.priorityLevel(VariantType.STOPLOSS);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel11() {
		int n = VariantType.priorityLevel(VariantType.FS_DUPLICATION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel12() {
		int n = VariantType.priorityLevel(VariantType.NON_FS_DUPLICATION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel13() {
		int n = VariantType.priorityLevel(VariantType.START_LOSS);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel15() {
		int n = VariantType.priorityLevel(VariantType.ncRNA_EXONIC);
		Assert.assertEquals(2, n);
	}

	@Test
	public void testPriorityLevel16() {
		int n = VariantType.priorityLevel(VariantType.ncRNA_SPLICE_DONOR);
		Assert.assertEquals(2, n);
		n = VariantType.priorityLevel(VariantType.ncRNA_SPLICE_ACCEPTOR);
		Assert.assertEquals(2, n);
		n = VariantType.priorityLevel(VariantType.ncRNA_SPLICE_REGION);
		Assert.assertEquals(2, n);
	}

	@Test
	public void testPriorityLevel17() {
		int n = VariantType.priorityLevel(VariantType.UTR3);
		Assert.assertEquals(3, n);
	}

	@Test
	public void testPriorityLevel18() {
		int n = VariantType.priorityLevel(VariantType.UTR5);
		Assert.assertEquals(4, n);
	}

	@Test
	public void testPriorityLevel19() {
		int n = VariantType.priorityLevel(VariantType.SYNONYMOUS);
		Assert.assertEquals(5, n);
	}

	@Test
	public void testPriorityLevel20() {
		int n = VariantType.priorityLevel(VariantType.INTRONIC);
		Assert.assertEquals(6, n);
	}

	@Test
	public void testPriorityLevel21() {
		int n = VariantType.priorityLevel(VariantType.ncRNA_INTRONIC);
		Assert.assertEquals(7, n);
	}

	@Test
	public void testPriorityLevel22() {
		int n = VariantType.priorityLevel(VariantType.UPSTREAM);
		Assert.assertEquals(8, n);
	}

	@Test
	public void testPriorityLevel23() {
		int n = VariantType.priorityLevel(VariantType.DOWNSTREAM);
		Assert.assertEquals(8, n);
	}

	@Test
	public void testPriorityLevel24() {
		int n = VariantType.priorityLevel(VariantType.INTERGENIC);
		Assert.assertEquals(9, n);
	}

	@Test
	public void testPriorityLevel25() {
		int n = VariantType.priorityLevel(VariantType.ERROR);
		Assert.assertEquals(10, n);
	}

	@Test
	public void testPriorityLevel26() {
		int n = VariantType.priorityLevel(VariantType.SV_DELETION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel27() {
		int n = VariantType.priorityLevel(VariantType.SV_INSERTION);
		Assert.assertEquals(1, n);
	}

	@Test
	public void testPriorityLevel28() {
		int n = VariantType.priorityLevel(VariantType.SV_SUBSTITUTION);
		Assert.assertEquals(1, n);
	}

	/*
	 * This tests that the constants returned by the function getPrioritySortedList() are arranged in monotonically
	 * non-decreasing order.
	 */
	@Test
	public void testOrderingOfPriorityLevel() {
		VariantType n[] = VariantType.getPrioritySortedList();
		for (int i = 1; i < n.length; ++i) {
			Assert.assertTrue(VariantType.priorityLevel(n[i - 1]) <= VariantType.priorityLevel(n[i]));
		}
	}

	@Test
	public void testFSDeletionString() {
		String s = VariantType.FS_DELETION.toDisplayString();
		Assert.assertEquals("frameshift truncation", s);
	}

	@Test
	public void isTopPriorityTest1() {
		boolean b = VariantType.MISSENSE.isTopPriorityVariant();
		Assert.assertEquals(true, b);
	}

	@Test
	public void isTopPriorityTest2() {
		boolean b = VariantType.UTR5.isTopPriorityVariant();
		Assert.assertEquals(false, b);
	}

	@Test
	public void isTopPriorityTest3() {
		boolean b = VariantType.DOWNSTREAM.isTopPriorityVariant();
		Assert.assertEquals(false, b);
	}

	@Test
	public void testSize() {
		Assert.assertEquals(33, VariantType.size());
	}

	@Test
	public void testToDisplayString() {
		Assert.assertEquals("transcript ablation", VariantType.TRANSCRIPT_ABLATION.toDisplayString());
		Assert.assertEquals("downstream", VariantType.DOWNSTREAM.toDisplayString());
		Assert.assertEquals("frameshift elongation", VariantType.FS_INSERTION.toDisplayString());
		Assert.assertEquals("inframe substitution", VariantType.NON_FS_SUBSTITUTION.toDisplayString());
		Assert.assertEquals("frameshift substitution", VariantType.FS_SUBSTITUTION.toDisplayString());
		Assert.assertEquals("missense", VariantType.MISSENSE.toDisplayString());
		Assert.assertEquals("inframe deletion", VariantType.NON_FS_DELETION.toDisplayString());
		Assert.assertEquals("inframe insertion", VariantType.NON_FS_INSERTION.toDisplayString());
		Assert.assertEquals("splice donor", VariantType.SPLICE_DONOR.toDisplayString());
		Assert.assertEquals("splice acceptor", VariantType.SPLICE_ACCEPTOR.toDisplayString());
		Assert.assertEquals("splice region", VariantType.SPLICE_REGION.toDisplayString());
		Assert.assertEquals("stopgain", VariantType.STOPGAIN.toDisplayString());
		Assert.assertEquals("stoploss", VariantType.STOPLOSS.toDisplayString());
		Assert.assertEquals("inframe duplication", VariantType.NON_FS_DUPLICATION.toDisplayString());
		Assert.assertEquals("frameshift duplication", VariantType.FS_DUPLICATION.toDisplayString());
		Assert.assertEquals("startloss", VariantType.START_LOSS.toDisplayString());
		Assert.assertEquals("ncRNA exonic", VariantType.ncRNA_EXONIC.toDisplayString());
		Assert.assertEquals("ncRNA intronic", VariantType.ncRNA_INTRONIC.toDisplayString());
		Assert.assertEquals("ncRNA splice donor", VariantType.ncRNA_SPLICE_DONOR.toDisplayString());
		Assert.assertEquals("ncRNA splice acceptor", VariantType.ncRNA_SPLICE_ACCEPTOR.toDisplayString());
		Assert.assertEquals("ncRNA splice region", VariantType.ncRNA_SPLICE_REGION.toDisplayString());
		Assert.assertEquals("UTR3", VariantType.UTR3.toDisplayString());
		Assert.assertEquals("UTR5", VariantType.UTR5.toDisplayString());
		Assert.assertEquals("synonymous", VariantType.SYNONYMOUS.toDisplayString());
		Assert.assertEquals("intronic", VariantType.INTRONIC.toDisplayString());
		Assert.assertEquals("upstream", VariantType.UPSTREAM.toDisplayString());
		Assert.assertEquals("downstream", VariantType.DOWNSTREAM.toDisplayString());
		Assert.assertEquals("intergenic", VariantType.INTERGENIC.toDisplayString());
		Assert.assertEquals("error", VariantType.ERROR.toDisplayString());
		Assert.assertEquals("1k+ deletion", VariantType.SV_DELETION.toDisplayString());
		Assert.assertEquals("1k+ insertion", VariantType.SV_INSERTION.toDisplayString());
		Assert.assertEquals("1k+ substitution", VariantType.SV_SUBSTITUTION.toDisplayString());
		Assert.assertEquals("1k+ inversion", VariantType.SV_INVERSION.toDisplayString());
	}

	@Test
	public void toSequenceOntologyTerm() {
		Assert.assertEquals("transcript_ablation", VariantType.TRANSCRIPT_ABLATION.toSequenceOntologyTerm());
		Assert.assertEquals("downstream_gene_variant", VariantType.DOWNSTREAM.toSequenceOntologyTerm());
		Assert.assertEquals("frameshift_elongation", VariantType.FS_INSERTION.toSequenceOntologyTerm());
		Assert.assertEquals("inframe_substitution", VariantType.NON_FS_SUBSTITUTION.toSequenceOntologyTerm());
		Assert.assertEquals("frameshift_substitution", VariantType.FS_SUBSTITUTION.toSequenceOntologyTerm());
		Assert.assertEquals("missense_variant", VariantType.MISSENSE.toSequenceOntologyTerm());
		Assert.assertEquals("inframe_deletion", VariantType.NON_FS_DELETION.toSequenceOntologyTerm());
		Assert.assertEquals("inframe_insertion", VariantType.NON_FS_INSERTION.toSequenceOntologyTerm());
		Assert.assertEquals("splice_donor_variant", VariantType.SPLICE_DONOR.toSequenceOntologyTerm());
		Assert.assertEquals("splice_acceptor_variant", VariantType.SPLICE_ACCEPTOR.toSequenceOntologyTerm());
		Assert.assertEquals("splice_region_variant", VariantType.SPLICE_REGION.toSequenceOntologyTerm());
		Assert.assertEquals("stop_gained", VariantType.STOPGAIN.toSequenceOntologyTerm());
		Assert.assertEquals("stop_lost", VariantType.STOPLOSS.toSequenceOntologyTerm());
		Assert.assertEquals("inframe_duplication", VariantType.NON_FS_DUPLICATION.toSequenceOntologyTerm());
		Assert.assertEquals("frameshift_duplication", VariantType.FS_DUPLICATION.toSequenceOntologyTerm());
		Assert.assertEquals("start_lost", VariantType.START_LOSS.toSequenceOntologyTerm());
		Assert.assertEquals("non_coding_exon_variant", VariantType.ncRNA_EXONIC.toSequenceOntologyTerm());
		Assert.assertEquals("non_coding_intron_variant", VariantType.ncRNA_INTRONIC.toSequenceOntologyTerm());
		Assert.assertEquals("non_coding_splice_donor_variant", VariantType.ncRNA_SPLICE_DONOR.toSequenceOntologyTerm());
		Assert.assertEquals("non_coding_splice_acceptor_variant",
				VariantType.ncRNA_SPLICE_ACCEPTOR.toSequenceOntologyTerm());
		Assert.assertEquals("non_coding_splice_region_variant",
				VariantType.ncRNA_SPLICE_REGION.toSequenceOntologyTerm());
		Assert.assertEquals("3_prime_UTR_variant", VariantType.UTR3.toSequenceOntologyTerm());
		Assert.assertEquals("5_prime_UTR_variant", VariantType.UTR5.toSequenceOntologyTerm());
		Assert.assertEquals("synonymous_variant", VariantType.SYNONYMOUS.toSequenceOntologyTerm());
		Assert.assertEquals("intron_variant", VariantType.INTRONIC.toSequenceOntologyTerm());
		Assert.assertEquals("upstream_gene_variant", VariantType.UPSTREAM.toSequenceOntologyTerm());
		Assert.assertEquals("downstream_gene_variant", VariantType.DOWNSTREAM.toSequenceOntologyTerm());
		Assert.assertEquals("intergenic_variant", VariantType.INTERGENIC.toSequenceOntologyTerm());
		Assert.assertEquals("error", VariantType.ERROR.toSequenceOntologyTerm());
		Assert.assertEquals("deletion", VariantType.SV_DELETION.toSequenceOntologyTerm());
		Assert.assertEquals("insertion", VariantType.SV_INSERTION.toSequenceOntologyTerm());
		Assert.assertEquals("substitution", VariantType.SV_SUBSTITUTION.toSequenceOntologyTerm());
		Assert.assertEquals("inversion", VariantType.SV_INVERSION.toSequenceOntologyTerm());
	}

	@Test
	public void toSequenceOntologyID() {
		Assert.assertEquals("SO:0001893", VariantType.TRANSCRIPT_ABLATION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001632", VariantType.DOWNSTREAM.toSequenceOntologyID());
		Assert.assertEquals("SO:0001909", VariantType.FS_INSERTION.toSequenceOntologyID());
		Assert.assertEquals("nonframeshift substitution", VariantType.NON_FS_SUBSTITUTION.toSequenceOntologyID());
		Assert.assertEquals("frameshift substitution", VariantType.FS_SUBSTITUTION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001583", VariantType.MISSENSE.toSequenceOntologyID());
		Assert.assertEquals("SO:0001822", VariantType.NON_FS_DELETION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001821", VariantType.NON_FS_INSERTION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001575", VariantType.SPLICE_DONOR.toSequenceOntologyID());
		Assert.assertEquals("SO:0001574", VariantType.SPLICE_ACCEPTOR.toSequenceOntologyID());
		Assert.assertEquals("SO:0001630", VariantType.SPLICE_REGION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001587", VariantType.STOPGAIN.toSequenceOntologyID());
		Assert.assertEquals("SO:0001578", VariantType.STOPLOSS.toSequenceOntologyID());
		Assert.assertEquals("nonframeshift duplication", VariantType.NON_FS_DUPLICATION.toSequenceOntologyID());
		Assert.assertEquals("frameshift duplication", VariantType.FS_DUPLICATION.toSequenceOntologyID());
		Assert.assertEquals("start loss", VariantType.START_LOSS.toSequenceOntologyID());
		Assert.assertEquals("SO:0001792", VariantType.ncRNA_EXONIC.toSequenceOntologyID());
		Assert.assertEquals("noncoding RNA intronic", VariantType.ncRNA_INTRONIC.toSequenceOntologyID());
		Assert.assertEquals("noncoding RNA splice donor", VariantType.ncRNA_SPLICE_DONOR.toSequenceOntologyID());
		Assert.assertEquals("noncoding RNA splice acceptor", VariantType.ncRNA_SPLICE_ACCEPTOR.toSequenceOntologyID());
		Assert.assertEquals("noncoding RNA splice region", VariantType.ncRNA_SPLICE_REGION.toSequenceOntologyID());
		Assert.assertEquals("SO:0001624", VariantType.UTR3.toSequenceOntologyID());
		Assert.assertEquals("SO:0001623", VariantType.UTR5.toSequenceOntologyID());
		Assert.assertEquals("SO:0001819", VariantType.SYNONYMOUS.toSequenceOntologyID());
		Assert.assertEquals("SO:0001627", VariantType.INTRONIC.toSequenceOntologyID());
		Assert.assertEquals("SO:0001631", VariantType.UPSTREAM.toSequenceOntologyID());
		Assert.assertEquals("SO:0001632", VariantType.DOWNSTREAM.toSequenceOntologyID());
		Assert.assertEquals("SO:0001628", VariantType.INTERGENIC.toSequenceOntologyID());
		Assert.assertEquals("error", VariantType.ERROR.toSequenceOntologyID());
		Assert.assertEquals("SO:0000159", VariantType.SV_DELETION.toSequenceOntologyID());
		Assert.assertEquals("SO:0000667", VariantType.SV_INSERTION.toSequenceOntologyID());
		Assert.assertEquals("SO:1000002", VariantType.SV_SUBSTITUTION.toSequenceOntologyID());
		Assert.assertEquals("SO:1000036", VariantType.SV_INVERSION.toSequenceOntologyID());
	}
}