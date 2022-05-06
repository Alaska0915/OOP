package a1;

/** An instance maintains info about the PhD of a person. */
public class PhD {
    private String name; // name of person with PhD, a String of length > 1.
    private int year; // Year the PhD was awarded. Can be any integer.
    private int month; // Month the PhD was awarded. In range 1..12
    private PhD ad1; // The first PhD advisor of this person —null if unknown.
    private PhD ad2; // The second advisor of this person —null if unknown or if the person has less
                     // than two advisors.
    private int advisee; // # of PhD advisees of this person

    /** Constructor: an instance with name n, PhD year y, and PhD month m.<br>
     * The advisors are unknown, and there are 0 advisees.<br>
     * Precondition: n has at least 2 chars, and m is in 1..12. */

    public PhD(String n, int y, int m) {
        assert n != null && n.length() >= 2;
        assert 1 <= m && m <= 12;

        this.name= n;
        this.year= y;
        this.month= m;
        this.ad1= null;
        this.ad2= null;
        this.advisee= 0;
    }

    /** Constructor: a PhD with name n, PhD year y, PhD month m, first advisor p1, and second
     * advisor p2.<br>
     * Precondition: n has at least 2 chars, m is in 1..12, p1 and p2 are not null, and p1 and p2
     * are different */
    public PhD(String n, int y, int m, PhD p1, PhD p2) {
        assert n != null && n.length() >= 2;
        assert 1 <= m && m <= 12;
        assert p1 != null && p2 != null && p1 != p2;

        this.name= n;
        this.year= y;
        this.month= m;
        this.ad1= p1;
        this.ad2= p2;
        this.advisee= 0;

        p1.advisee+= 1;
        p2.advisee+= 1;
    }

    /** Return the name of this person. */
    public String name() {
        return name;
    }

    /** Return the date this person got their PhD in the form "month/<year>" <br>
     * E.g. For February 2022, return "2/2022". */
    public String date() {
        return Integer.toString(month) + "/" + Integer.toString(year);
    }

    /** Return the first advisor of this PhD (null if unknown). */
    public PhD advisor1() {
        return ad1;
    }

    /** Return the second advisor of this PhD (null if unknown or non-existent) */
    public PhD advisor2() {
        return ad2;
    }

    /** Return the number of PhD advisees of this person */
    public int advisees() {
        return advisee;
    }

    /** Add p as the first advisor of this person. <br>
     * Precondition: the first advisor is unknown and p is not null. */
    public void addAdvisor1(PhD p) {
        assert ad1 == null && p != null;

        ad1= p;
        p.advisee+= 1;
    }

    /** Add p as the second advisor of this PhD. <br>
     * Precondition: The first advisor is known, the second advisor is unknown, p is not null, and p
     * is different from the first advisor. */
    public void addAdvisor2(PhD p) {
        assert ad1 != null && ad2 == null && p != null && p != ad1;

        ad2= p;
        p.advisee+= 1;
    }

    /** Return value of: "p is not null and this PhD got the PhD before p" */
    public boolean gotBefore(PhD p) {
        assert p != null;

        return (this.year < p.year) || (this.year == p.year && this.month < p.month);
    }

    /** Return value of: "this PhD is an intellectual sibling of p".<br>
     * Precondition: p is not null. */
    public boolean isSiblingOf(PhD p) {
        assert p != null;

        return this != p // can't be sibling if it's the same person
            && (((this.ad1 == p.ad1 || this.ad1 == p.ad2) && this.ad1 != null) ||
                ((this.ad2 == p.ad1 || this.ad2 == p.ad2) && this.ad2 != null));

    }

}
