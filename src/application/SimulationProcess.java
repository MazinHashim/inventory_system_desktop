package application;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SimulationProcess {

	static Scanner scanner = new Scanner(System.in);
	

	public static ObservableList<Refrigrator> SimAlgorithm(int days) {
		ObservableList<Refrigrator> list = FXCollections.observableArrayList();
		
		Refrigrator refrig = new Refrigrator();
		
		System.out.println("Days\tBegin Inventory\t\tRandomRDA\tRef Demand\tEnd Inv\t\tShortage\tOrder\t\tRandomRDA\tLead Time\n");
		refrig.beginingInv = 3;
		for (int i = 1; i <= days; i++) {
			refrig.randDem = (int) (Math.random() * 100)+1;
			refrig.RefDemand = Generator(refrig.randDem,designController.refDemandProb,0);
			End_With_Shortage(refrig);
			int begin = refrig.endingInv;
			if(i == 1) {
				refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - refrig.endingInv) + refrig.shortageQuan;
				refrig.leadTime = 1;
				refrig.count = 0;
			}
			refrig.count++;
			if(refrig.leadTime == 0) {
				if(refrig.shortageQuan != 0) {
					refrig.orderQuan -= refrig.shortageQuan;
					refrig.shortageQuan = 0;
				}
				begin = refrig.endingInv + refrig.orderQuan;
				refrig.leadTime+=5;
			}else refrig.leadTime --;
			
			if(i % Refrigrator.REVIEW_DAYS == 0) {
				refrig.orderQuan = (Refrigrator.ORDER_UP_TO_LEVEL - refrig.endingInv) + refrig.shortageQuan;
				refrig.rmled =  (int) (Math.random() * 10)+1;
				refrig.leadTime = Generator(refrig.rmled,designController.leadTimeProb,1);
			}
			System.out.println(refrig);
			list.add(refrig);
			refrig.beginingInv = begin;
			System.out.println("----------------------------------------"
					+ "-------------------------------------------------"
					+ "-------------------------------------------------");
		}
		return list;
		
	}
	private static void End_With_Shortage(Refrigrator ref) {
		if(ref.beginingInv < ref.RefDemand) {
			ref.shortageQuan +=  (ref.RefDemand - ref.beginingInv);
			ref.endingInv = 0;
		}
		else
			ref.endingInv =  ref.beginingInv - ref.RefDemand;
	}
	private static int Generator(int rand,ObservableList<Refrigrator> RDA,int limit) {
		
		for (int i = 0; i < RDA.size(); i++) {
			if(rand>0 && rand<=(RDA.get(i).leadRDA == 0 ? RDA.get(i).refRDA : RDA.get(i).leadRDA)) {
				return i+limit;
			}
		}
		return -1;
	}
	public static void display(ObservableList<Double> RDA,String randVar,int limit){
		System.out.println("Demand\tRDA Of "+randVar);
		System.out.println(limit+"\t"+1+" --> "+RDA.get(0));
		for(int i=1; i<RDA.size(); i++){
			System.out.println(i+limit+"\t"+(RDA.get(i-1)+1)+" --> "+RDA.get(i));
		}
		System.out.println();
	}
}

	
////	if(refrig.leadTime>0 && refrig.leadTime<=leadTimeProb[0]) {
////		System.out.print("\t\t\t"+refrig.leadTime+"\t1\n");
////	}else if(refrig.leadTime>leadTimeProb[0] && refrig.leadTime<=leadTimeProb[1]) {
////		System.out.print("\t\t\t"+refrig.leadTime+"\t2\n");
////	}else if(refrig.leadTime<leadTimeProb[1] && refrig.leadTime<=leadTimeProb[2]) {
////		System.out.print("\t\t\t"+refrig.leadTime+"\t3\n");
////	}
//	
////	if(ref.RefDemand>0 && ref.RefDemand<=refDemandProb[0]) {
////		return 0;
////	}else if(ref.RefDemand>refDemandProb[0] && ref.RefDemand<=refDemandProb[1]) {
////		return 1;
////	}else if(ref.RefDemand<refDemandProb[1] && ref.RefDemand<=refDemandProb[2]) {
////		return 2;
////	}else if(ref.RefDemand<refDemandProb[2] && ref.RefDemand<=refDemandProb[3]) {
////		return 3;
////	}else if(ref.RefDemand<refDemandProb[3] && ref.RefDemand<=refDemandProb[4]) {
////		return 4;
////	}
//
//	public static void display(double[] RDA,String randVar,int limit){
//		System.out.println("Demand\tRDA Of "+randVar);
//		System.out.println(limit+"\t"+1+" --> "+RDA[0]);
//		for(int i=1; i<RDA.length; i++){
//			System.out.println(i+limit+"\t"+(RDA[i-1]+1)+" --> "+RDA[i]);
//		}
//		System.out.println();
//	}
//}
