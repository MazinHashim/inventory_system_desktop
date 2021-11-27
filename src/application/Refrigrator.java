package application;


public class Refrigrator {
	
	public double refProp,refRDA,leadProp,leadRDA;
	public static final int ORDER_UP_TO_LEVEL = 11;
	public static final int REVIEW_DAYS = 5;
	public int count;
	public int beginingInv;
	public int randDem;
	public int RefDemand;
	public int endingInv;
	public int shortageQuan;
	public int orderQuan;
	public int rmled;
	public int leadTime;
	
//	public Refrigrator(int count, int beginingInv, int randDem, int RefDemand, int endingInv, int shortageQuan, int orderQuan, int rmled,
//			int leadTime) {
//		
//		this.count = count;
//		this.beginingInv = beginingInv;
//		this.randDem = randDem;
//		this.RefDemand = RefDemand;
//		this.endingInv = endingInv;
//		this.shortageQuan = shortageQuan;
//		this.orderQuan = orderQuan;
//		this.rmled = rmled;
//		this.leadTime = leadTime;
//		
//	}
	public Refrigrator(double refProp,double leadProp) {		
		this.leadProp = leadProp;
		this.refProp = refProp;
		this.refRDA = this.refProp * 100;
		this.leadRDA = this.leadProp * 10;
	}
	public Refrigrator() {}
//	@Override
//	public String toString() {
//		
//		return count+"\t\t"+beginingInv+"\t\t"+randDem+"\t\t"+RefDemand+"\t\t"+
//				endingInv+"\t\t"+shortageQuan+"\t\t"+orderQuan+"\t\t"+rmled+"\t\t"+leadTime;
//	}
	
	
}
