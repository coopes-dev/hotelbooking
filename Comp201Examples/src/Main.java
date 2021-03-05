import java.util.Vector;

public class Main {
	
	
	
	private void testPersonCompositeExample() {
		System.out.println("Running composite example..");
		DateOfBirth dob=new DateOfBirth(1,1,1963);
		Address address1=new Address("L69 3BX","Ashton Street","","","Liverpool","Merseyside","GBR");
		PersonCompositeExample	person=new PersonCompositeExample("Coope","Seb","",dob,address1);
		System.out.println("Post town is "+person.getAddress().getPostTownCity());
		System.out.println("Post code is "+person.getAddress().getPostcode().getCode());
		person.getAddress().setPostTownCity("Sheffield");
		address1.setPostTownCity("Sheffield");
		System.out.println("Post town after change is.. "+person.getAddress().getPostTownCity());
	}
	
	private void testPersonAggregateExample() {
		System.out.println("Running aggregate example..");
		DateOfBirth dob=new DateOfBirth(1,1,1963);
		Address address1=new Address("L69 3BX","Ashton Street","","","Liverpool","Merseyside","GBR");
		PersonAggregateExample	person=new PersonAggregateExample("Coope","Seb","",dob,address1);
		System.out.println("Post town is "+person.getAddress().getPostTownCity());
		System.out.println("Post code is "+person.getAddress().getPostcode().getCode());
		person.getAddress().setPostTownCity("Sheffield");
		System.out.println("Post town after change is.. "+person.getAddress().getPostTownCity());
	}
	
	private void testPersonMultipleAddressExample() {
		System.out.println("Running aggregate example..");
		DateOfBirth dob=new DateOfBirth(1,1,1963);
		Address address1=new Address("L69 3BX","Ashton Street","","","Liverpool","Merseyside","GBR");
		Address address2=new Address("PR9 3EF","Preston Road","","","Southport","Merseyside","GBR");
		PersonMultipleAddress	person=new PersonMultipleAddress("Coope","Seb","",dob);
		person.addAddress(address1);
		person.addAddress(address2);		
		Vector <Address> addressBook=person.getAddressBook();
		addressBook.forEach((addressInList) -> {
			System.out.println("Post code is "+addressInList.getPostcode().getCode());				
		});
		
	}
	
	
	
	// Do some testing run's of the classes
	private void test() {
		testPersonCompositeExample();
		testPersonAggregateExample();
		testPersonMultipleAddressExample();
	}

	public static void main(String[] args) {
		Main main=new Main();
		main.test();
	}

}
