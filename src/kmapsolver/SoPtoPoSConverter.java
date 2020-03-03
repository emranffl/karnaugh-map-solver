package kmapsolver;

class SoPtoPoSConverter {

    public String convert(String sop) {
        String iteration1 = sop.replace(" + ", ") * (");
        String iteration2 = "(" + iteration1.replace("'", " + ") + ")";
        String iteration3 = "";

        for (int i = 0; i < iteration2.length(); i++) {
            String ch;
            if (iteration2.charAt(i) == 'A' || iteration2.charAt(i) == 'B' || iteration2.charAt(i) == 'C' || iteration2.charAt(i) == 'D') {
                if (!iteration2.regionMatches(i + 1, " + ", 0, 3)) {
                    ch = iteration2.charAt(i) + "' + ";
                } else {
                    ch = Character.toString(iteration2.charAt(i));
                }
            } else {
                ch = Character.toString(iteration2.charAt(i));
            }
            iteration3 += ch;
        }
        return iteration3.replace(" + )", ")");
    }
}
