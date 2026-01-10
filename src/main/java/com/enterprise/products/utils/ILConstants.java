package com.enterprise.products.utils;

public final class ILConstants {
	public static final String ADMIN_ROLE = "Admin";
	public static final String USER_ROLE = "User";
	public static final String VIEW_ROLE = "View";
	
	public static final String INNOVARE_LABS_TOPIC = "InnovareLabsTopic9";
	//public static final String INNOVARE_LABS_GROUP = "innovarelabs-group";
	public static final String INNOVARE_LABS_GROUP_ID = "test-group-4";
	public static final String INNOVARE_LABS_PARTITION = "0";
	public static final String INNOVARE_LABS_KAFKA_URL = "localhost:9092";
	//public static final String INNOVARE_LABS_KAFKA_URL = "host.docker.internal:9092";
	//public static final String INNOVARE_LABS_KAFKA_URL="b-3-public.demokannacluster1.vg0hc5.c7.kafka.us-east-2.amazonaws.com:9198,b-2-public.demokannacluster1.vg0hc5.c7.kafka.us-east-2.amazonaws.com:9198,b-1-public.demokannacluster1.vg0hc5.c7.kafka.us-east-2.amazonaws.com:9198";
	//public static final String INNOVARE_LABS_KAFKA_URL="b-3-public.kafkacluster.40df6s.c7.kafka.us-east-2.amazonaws.com:9198,b-2-public.kafkacluster.40df6s.c7.kafka.us-east-2.amazonaws.com:9198,b-1-public.kafkacluster.40df6s.c7.kafka.us-east-2.amazonaws.com:9198";
	public static final String INNOVARE_LABS_AUTO_OFFSET_RESET_CONFIG = "earliest";
	public static final String ORDER_FILE_PATH = "C:/kafka-data/orders.txt";
	//public static final String ORDER_FILE_PATH = "/kafka-data/orders.txt";
	
	//Micro service URL's
	public static final String MICROSERVICE_RESTFUL_MANUFACTURER_URL ="http://localhost:8091/api/manufacturer";
	public static final String MICROSERVICE_RESTFUL_PRODUCT_URL ="http://localhost:8092/api/product";
	public static final String MICROSERVICE_RESTFUL_ORDERQTY_URL ="http://localhost:8093/api/orderqty";
	//public static final String MICROSERVICE_RESTFUL_MANUFACTURER_URL ="http://host.docker.internal:8091/api/manufacturer";
	//public static final String MICROSERVICE_RESTFUL_PRODUCT_URL ="http://host.docker.internal:8092/api/product";
	//public static final String MICROSERVICE_RESTFUL_ORDERQTY_URL ="http://host.docker.internal:8093/api/orderqty";
	
	//public static final String MICROSERVICE_RESTFUL_MANUFACTURER_URL ="http://ec2-3-129-20-132.us-east-2.compute.amazonaws.com:8091/api/manufacturer";
	//public static final String MICROSERVICE_RESTFUL_PRODUCT_URL ="http://ec2-18-191-140-40.us-east-2.compute.amazonaws.com:8092/api/product";
	//public static final String MICROSERVICE_RESTFUL_ORDERQTY_URL ="http://ec2-3-16-139-8.us-east-2.compute.amazonaws.com:8093/api/orderqty";
	
	
	
	//MongoDB Operations
	public static final String MONGODB_OPERATION_SAVE = "SAVE";
	public static final String MONGODB_OPERATION_UPDATE = "UPDATE";
	public static final String MONGODB_OPERATION_DELETE = "DELETE";
	
	
	public static final String DEPARTMENT_EXPENSE_TYPE_FIELD_MAP_STR = "ExpenseId|Description";
	public static final String DAY_BY_DAY_EXPENSES_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|ExpenseType|ExpenseDescription|TransactionDescription|SubExpenseType|StDebits|StCredits|ChequeNo|ReceiptInvoice|VochurNo|Responsible|Comments";	
	//Vendor material Type
	public static final String VENDOR_MATERIAL_EXPENSES_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|TripSheetNo|StQtyMts|StQtyUnits|StFinalQty|StRate|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_SAND_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|TripSheetNo|StQtyMts|SandLocation|StQtyUnits|StFinalQty|StRate|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_BRICKS_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|StQtyNo|StRate|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_STEEL_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|SteelType|StQtyMts|StRate|StSteelAssessableValue|StSteelExcise|StSteelSubTotal|StSteelVat|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_CEMENT_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|StNumberOfBags|StRate|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_WATER_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|VechicleNo|TripSheetNo|StQtyNo|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_MATERIAL_EXPENSES_MISCELLANEOUS_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|StQtyNo|StRate|StEstimatedAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	
	//Vendor machine type
	public static final String VENDOR_MACHINE_EXPENSES_JCB_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|MachineType|TypeOfWork|VechicleNo|TripSheetNo|StTimeDuration|StRate|StAmount|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	
	//Vendor labour type
	public static final String VENDOR_LABOUR_EXPENSES_KALASI_CHARGES_FIELD_MAP_STR = "StXlsPrimaryKey|StTransactionDate|VendorName|Description|StPerNosQty|StPerNosRate|StPerNosTotal|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	public static final String VENDOR_LABOUR_EXPENSES_LABOUR_ATTADENCE_FIELD_MAP_STR = "StXlsPrimaryKey|VendorName|Type|SubType|StTransactionDate|StMaleMason|StMaleMasonRate|stMaleMasonTotal|StMaleHelper|StMaleHelperRate|StMaleHelperTotal|"
			+ "StFemaleHelper|StFemaleHelperRate|StFemaleHelperTotal|StAddPerson|StAddPersonRate|StAddPersonTotal|PaymentApprovedBy|PaymentStatus|StPaidDate|PaymentMethod|ChequeNo|StChequeAmount|Comments";
	
	//Load department expense type
	public static final String LOAD_DEPARTMENT_EXPENSE_TYPE_ID = "DEPARTMENT_EXPENSE_TYPE";
	public static final String LOAD_DEPARTMENT_EXPENSE_TYPE_DESCRIPTION = "LOAD - DEPARTMENT EXPENSE TYPE";
	
	//Load day by day expenses
	public static final String LOAD_DAY_BY_DAY_EXPENSES_ID = "DAY_BY_DAY_EXPENSES";
	public static final String LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES";
		
	//Load vendor material expenses
	public static final String LOAD_VENDOR_MATERIAL_EXPENSES_ID = "VENDOR_MATERIAL_EXPENSES";
	public static final String LOAD_VENDOR_MATERIAL_EXPENSES_DESCRIPTION = "LOAD - VENDOR MATERIAL EXPENSES";
	
	//Load vendor machine expenses
	public static final String LOAD_VENDOR_MACHINE_EXPENSES_ID = "VENDOR_MACHINE_EXPENSES";
	public static final String LOAD_VENDOR_MACHINE_EXPENSES_DESCRIPTION = "LOAD - VENDOR MACHINE EXPENSES";	
	
	//Load vendor labour expenses
	public static final String LOAD_VENDOR_LABOUR_EXPENSES_ID = "VENDOR_LABOUR_EXPENSES";
	public static final String LOAD_VENDOR_LABOUR_EXPENSES_DESCRIPTION = "LOAD - VENDOR LABOUR EXPENSES";		
	
	//Report department expense type
	public static final String REPORT_DEPARTMENT_EXPENSE_TYPE_ID = "REPORT_DEPARTMENT_EXPENSE_TYPE_ID";
	public static final String REPORT_DEPARTMENT_EXPENSE_TYPE_DESCRIPTION = "REPORT - DEPARTMENT EXPENSE TYPE";	
	
	//Report day by day expenses type
	public static final String REPORT_DAY_BY_DAY_EXPENSES_ID = "REPORT_DAY_BY_DAY_EXPENSES_ID";
	public static final String REPORT_DAY_BY_DAY_EXPENSES_DESCRIPTION = "REPORT - DAY BY DAY EXPENSES";	
	
	//Report vendor material expenses
	public static final String REPORT_VENDOR_MATERIAL_EXPENSES_ID = "REPORT_VENDOR_MATERIAL_EXPENSES_ID";
	public static final String REPORT_VENDOR_MATERIAL_EXPENSES_DESCRIPTION = "REPORT - VENDOR MATERIAL EXPENSES";
	
	//Report vendor machine expenses
	public static final String REPORT_VENDOR_MACHINE_EXPENSES_ID = "REPORT_VENDOR_MACHINE_EXPENSES_ID";
	public static final String REPORT_VENDOR_MACHINE_EXPENSES_DESCRIPTION = "REPORT - VENDOR MACHINE EXPENSES";
		
	//Report vendor labour expenses
	public static final String REPORT_VENDOR_LABOUR_EXPENSES_ID = "REPORT_VENDOR_LABOUR_EXPENSES_ID";
	public static final String REPORT_VENDOR_LABOUR_EXPENSES_DESCRIPTION = "REPORT - VENDOR LABOUR EXPENSES";
		
	
	//Material type
	public static final String SAND_DESCRIPTION = "SAND";
	public static final String BRICKS_DESCRIPTION = "BRICKS";
	public static final String STEEL_DESCRIPTION = "STEEL";
	public static final String CEMENT_DESCRIPTION = "CEMENT";
	public static final String WATER_DESCRIPTION = "WATER";
	public static final String MISCELLANEOUS_DESCRIPTION = "MISCELLANEOUS";
	
	//Machine type
	public static final String JCB_DESCRIPTION = "J.C.B";
	public static final String ROAD_ROLLER_DESCRIPTION = "ROAD ROLLER";
	public static final String HITACHI_DESCRIPTION = "HITACHI";
	public static final String DOZER_DESCRIPTION = "DOZER";
	public static final String DAILY_DIESEL_DESCRIPTION = "DAILY DIESEL CONSUMPTION & STOCK REGISTER";	
	
	//Labour type
	public static final String LABOUR_ATTADENCE_DESCRIPTION = "LABOUR ATTADENCE";
	public static final String KALASI_CHARGES_DESCRIPTION = "KALASI CHARGES";	
	
	public static final String PAYMENT_STATUS_PAID = "PAID";
	public static final String PAYMENT_STATUS_PENDING = "PENDING";
	
/*	//Civil
	public static final String CIVIL_DAY_BY_DAY_EXPENSES_ID = "CIVIL_DAY_BY_DAY_EXPENSES";
	public static final String CIVIL_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - CIVIL";
	//RD
	public static final String RD_DAY_BY_DAY_EXPENSES_ID = "RD_DAY_BY_DAY_EXPENSES";
	public static final String RD_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - R&D";	
	//Admin
	public static final String ADMIN_DAY_BY_DAY_EXPENSES_ID = "ADMIN_DAY_BY_DAY_EXPENSES";
	public static final String ADMIN_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - ADMIN";	
	//Share Application
	public static final String SHARE_APPLICATION_DAY_BY_DAY_EXPENSES_ID = "SHARE_APPLICATION_DAY_BY_DAY_EXPENSES";
	public static final String SHARE_APPLICATION_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - SHARE APPLICATION";	
	//Share Premium
	public static final String SHARE_PREMIUM_DAY_BY_DAY_EXPENSES_ID = "SHARE_PREMIUM_DAY_BY_DAY_EXPENSES";
	public static final String SHARE_PREMIUM_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - SHARE PREMIUM";	
	//Payroll
	public static final String PAY_ROLL_DAY_BY_DAY_EXPENSES_ID = "PAY_ROLL_DAY_BY_DAY_EXPENSES";
	public static final String PAY_ROLL_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - PAY ROLL";	
	//Equpment
	public static final String EQUIPMENT_DAY_BY_DAY_EXPENSES_ID = "EQUIPMENT_DAY_BY_DAY_EXPENSES";
	public static final String EQUIPMENT_LOAD_DAY_BY_DAY_EXPENSES_DESCRIPTION = "LOAD - DAY BY DAY EXPENSES - EQUIPMENT";*/
}
