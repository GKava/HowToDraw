package guide.drawings.drawing_3d.howtodraw;

import java.util.ArrayList;
import java.util.List;




public class Item {
    private int imageName1;
    private String txt_card;

    public Item(int imageName1, String txt_card){
        this.imageName1 = imageName1;
        this.txt_card = txt_card;
    }

    public String getTxt_card() {
        return txt_card;
    }

    public void setTxt_card(String txt_card) {
        this.txt_card = txt_card;
    }

    public void setImageName1(int imageName1) {
        this.imageName1 = imageName1;
    }


    public int getImageName1() {

        return imageName1;
    }

    public static List<Item> getAllItems(){
        ArrayList<Item> itemList = new ArrayList<>();

        return itemList;
    }
}

