public class RegexSampleClasss {

    public static void main(String args[]){

        System.out.println("Hello java is working");
        RegexSampleClasss r = new RegexSampleClasss();
        System.out.println(r.returnWord("123sd"));

    }

    public String returnWord(String word)
    {
        return word.replaceAll("[\\D]", "");
    }
}
