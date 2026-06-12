public class PdfDocument implements Document{

    @Override
    public void open() {
        System.out.println("Opening Pdf Document!!");
    }

    @Override
    public void safe() {
        System.out.println("Saving Pdf Document!!");
    }
}