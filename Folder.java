import java.util.ArrayList;

public class Folder extends Component{

    private String name;
    static int position = 1;
    static int tour=0;
	private ArrayList<Component> components = new ArrayList<Component>();

    public Folder(String vName){
        this.name = vName;
    }

    public void addElement(Component component){
        this.components.add(component);
    }
 
    public void showElement(){
        System.out.println(this.name);
        for (Component composant : this.components) 
		{
            
            
			if (composant instanceof Folder) 
			{ 
                deplace(position);
                position++; 
                System.out.print("├───── ");  
				((Folder)composant).showElement(); 
                position--; 
                tour++;
			}
			else if (composant instanceof Fic) 
			{
                deplace(position);
                System.out.print("├───── ");  
				((Fic)composant).showElement();
			}
			else
			{
				System.out.println("Unknows Type Error");
			}
		}
    }

    public void deplace(int position){

            for(int i=0; i<position; i++){
                System.out.print("│");
                System.out.print("       ");
           }   
                
            
        }         
}

