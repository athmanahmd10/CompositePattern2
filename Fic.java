public class Fic extends Component{

    private String name;
    public int position=0;
    public Fic(String vName){
        this.name = vName;
    }
    
    public void showElement(){
        System.out.println(this.name);
    }
}
