import java.lang.*;
import java.util.*;

// create table student(RID int, Name varchar(255), Salary int);
// Database table / schema
class Student
{
    public int RID;
    public String Name;
    public int Salary;

    private static int Generator;

    static
    {
        Generator = 0;
    }

    public Student(String str, int value)
    {
        this.RID = ++Generator;
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData()
    {
        System.out.println(this.RID + "\t" + this.Name + "\t" + this.Salary);
    }
}

class DBMS
{
   public LinkedList <Student> lobj;

    public DBMS()
    {
        lobj = new LinkedList<>();
    }

    public void StartDBMS()
    {
    	Scanner sc = new Scanner(System.in);
        System.out.println("Marvellous customised DBMS started succesfully....");

        String query = "";
        while(true)
        {
        	System.out.print("Mrevellous DBMS Console $");

        	query = sc.nextLine();

        	query = query.toLowerCase();

        	String tokens[] = query.split(" ");

        	int querysize = tokens.length;

        	if(querysize==1)
        	{

        		if("help".equals(tokens[0]))
        		{
        			System.out.println("This application is used to show Customised DBMS");
        			System.out.println("*************************************************************");
        			System.out.println("Terminate DBMS:--->exit");
        			System.out.println("Display all data:---> select * from student");	
        			System.out.println("Insert data :---> insert into student Name Salary");
        			System.out.println("delete record:--->delete student where RID = RID");
        			System.out.println("delete record:--->delete student where name = name");
        			System.out.println("select Min salary:--->select min salary from student");
        			System.out.println("select max salary:--->select max salary from student");
        			System.out.println("Total Count of Record:---> select count from student");



        		}
        		else if("exit".equals(tokens[0]))
        		{
        			System.out.println("Thank You using for marvellous DBMS");
        			break;
        		}

        	}
        	else if(querysize==6)
        	{

        		if("delete".equals(tokens[0]))
        		{

        			if("rid".equals(tokens[3]) && "where".equals(tokens[2]))
        			{

        			DeleteSpecific(Integer.parseInt(tokens[5]));

        			DisplayAll();

        			}

        			else if("name".equals(tokens[3]) && "where".equals(tokens[2]))
        			{

        			DeleteSpecific(tokens[5]);

        			DisplayAll();

        			}
        			
        		}

        		

        	}
        	else if(querysize==4)
        	{
        		if("select".equals(tokens[0]))
        		{
        			if("*".equals(tokens[1]))
        			{
        				if("from".equals(tokens[2]))
        				{
        					if("student".equals(tokens[3]))
        					{
        						DisplayAll();
        					}
        				}


        			}
        			
        		}
        		if("select".equals(tokens[0]))
        		{
        			if("count".equals(tokens[1]))
        			{
        				if("from".equals(tokens[2]))
        				{
        					if("student".equals(tokens[3]))
        					{
        						AggregateCount();
        					}
        				}


        			}
        			
        		}



        	}
        	else if(querysize==5)
        	{
        		if("insert".equals(tokens[0]))
        		{
        			if("into".equals(tokens[1]))
        			{
						if("student".equals(tokens[2]))
        				{
        					InsertData(tokens[3],Integer.parseInt(tokens[4]));

        				}
        			}
        		}

        		else if("select".equals(tokens[0]) &&"min".equals(tokens[1]))
        		{
        			if("salary".equals(tokens[2]) && "from".equals(tokens[3]))
        			{
        				if("student".equals(tokens[4]))
        				{
        					AggregateMin();
        				}
        			}
        		}

        		else if("select".equals(tokens[0]) &&"max".equals(tokens[1]))
        		{
        			if("salary".equals(tokens[2]) && "from".equals(tokens[3]))
        			{
        				if("student".equals(tokens[4]))
        				{
        					AggregateMax();
        				}
        			}
        		}
        	}

        }


    }

    // Insert into query
    public void InsertData(String str, int value)
    {
        Student sobj = new Student(str,value);
        lobj.add(sobj);
    }

    // Display all without condition
    public void DisplayAll()
    {
        for(Student sref : lobj)
        {
            sref.DisplayData();
        }
    }

    // Display by RID
    public void DisplaySpecific(int rid)
    {
         for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.DisplayData();
                break;
            }
        }
    }

    // Display by name
    public void DisplaySpecific(String str)
    {
         for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.DisplayData();
            }
        }
    }

    // Delete by RID
    public void DeleteSpecific(int rid)
    {
        int index = 0;

        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                lobj.remove(index);
                break;
            }
            index++;
        }
    }

    // Delete by Name
    public void DeleteSpecific(String str)
    {
        int index = 0;

        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                lobj.remove(index);
                break;
            }
            index++;
        }
    }

    public void AggregateMax()
    {
        int iMax = 0;
        Student temp = null;

        for(Student sref : lobj)
        {
            if(sref.Salary > iMax)
            {
                iMax = sref.Salary;
                temp = sref;
            }
        }

        System.out.println("Information of student having maximum salary : ");
        temp.DisplayData();
    }

    public void AggregateMin()
    {
        int iMin = (lobj.getFirst()).Salary;
        Student temp = lobj.getFirst();

        for(Student sref : lobj)
        {
            if(sref.Salary < iMin)
            {
                iMin = sref.Salary;
                temp = sref;
            }
        }

        System.out.println("Information of student having minimum salary : ");
        temp.DisplayData();
    }

    public void AggregateSum()
    {
        long iSum=0;
        for(Student sref : lobj)
        {
            iSum = iSum + sref.Salary;
        }

        System.out.println("Summation of salary : " + iSum);
        
    }

    public void AggregateAvg()
    {
        long iSum=0;
        for(Student sref : lobj)
        {
            iSum = iSum + sref.Salary;
        }

        System.out.println("Average salary is : " + iSum/(lobj.size()));
        
    }

    public void AggregateCount()
    {

    	 System.out.println("Count is : " + lobj.size());      

    }



}

class program151
{
    public static void main(String arg[])
    {
        DBMS dobj = new DBMS();

        dobj.StartDBMS();
        // dobj.InsertData("Piyush",1000);
        // dobj.InsertData("Sakshi",3000);
        // dobj.InsertData("Sachin",2000);
        // dobj.InsertData("Kiran",8000);
        // dobj.InsertData("Suraj",9000);
        // dobj.InsertData("Ruturaj",4000);

        // //dobj.DisplaySpecific(2);
        // //dobj.DisplaySpecific("Piyush");

        // //dobj.DeleteSpecific(2);
        // //dobj.DeleteSpecific("Piyush");
        // //dobj.DisplayAll();

        // dobj.AggregateMax();
        // dobj.AggregateMin();

        // dobj.AggregateSum();
        // dobj.AggregateAvg();
        // dobj.AggregateCount();
        // dobj.DisplayAll();



    }
}