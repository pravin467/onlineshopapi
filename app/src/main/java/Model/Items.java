package Model;

public class Items {
    private String itemname,itemimagename,itemdescription;
    private float itemprice;

    public Items(String itemname, String itemimagename, String itemdescription, float itemprice) {
        this.itemname = itemname;
        this.itemimagename = itemimagename;
        this.itemdescription = itemdescription;
        this.itemprice = itemprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemimagename() {
        return itemimagename;
    }

    public void setItemimagename(String itemimagename) {
        this.itemimagename = itemimagename;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public float getItemprice() {
        return itemprice;
    }

    public void setItemprice(float itemprice) {
        this.itemprice = itemprice;
    }
}
