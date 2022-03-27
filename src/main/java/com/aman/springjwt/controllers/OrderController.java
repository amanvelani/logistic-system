package com.aman.springjwt.controllers;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aman.springjwt.security.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class OrderController {
    @Autowired
	private UserService userservice;

    
    @RequestMapping("/order/goods")
	public void  orderGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<String[]> lt =  userservice.showGoods("approved_dt");
		ResultSetMetaData rs = userservice.metaData("goods");
		List<String> meta = new ArrayList<String>();
		int count = rs.getColumnCount();
			for(int i = 1; i<=count; i++) {
				String a = rs.getColumnName(i);
				meta.add(a);
			}
		String listString = String.join(", ", meta);

		System.out.println(listString);
		String[] array = new String[lt.size()];
		int i = 0;
		for(String[] l : lt) {
			array[i] = Arrays.toString(l);
			i++;
		}
		for(int b=0; b<lt.size(); b++) {
			//System.out.println(array[b]);
		}
		request.setAttribute("metaData", listString);
		request.setAttribute("data", array);
		
		RequestDispatcher rd = request.getRequestDispatcher("/order/goods.jsp");
		 rd.forward(request, response);
	}

    @RequestMapping("/order/placeOrder")
	public ModelAndView addgoods(@RequestParam("client_id") String clientid, @RequestParam("item_id") String itemid, @RequestParam("quantity") String quantity) {
		ModelAndView mv = new ModelAndView();
		
		if(!userservice.placeOrder(clientid,itemid,quantity)) {
			mv.setViewName("/manager/");
		}
		else {
			mv.setViewName("/manager/goods");
		}
		return mv;
		
	}


	// public class invoice {
	// 	PDDocument invc;
	// 	int n; 
	// 	Integer total = 0;
	// 	Integer price;
	// 	String CustName;
	// 	String CustPh;
	// 	List<String> ProductName = new ArrayList<String>();
	// 	List<Integer> ProductPrice = new ArrayList<Integer>();
	// 	List<Integer> ProductQty = new ArrayList<Integer>();
	// 	String InvoiceTitle = new String("CodeSpeedy Technology Private Limited");
	// 	String SubTitle = new String("Invoice");
		
	// 	//Using the constructor to create a PDF with a blank page
	// 	invoice() {
	// 	  //Create Document
	// 	  invc = new PDDocument();
	// 	  //Create Blank Page
	// 	  PDPage newpage = new PDPage();
	// 	  //Add the blank page
	// 	  invc.addPage(newpage);
	// 	}
		
	// 	//getdata() method is used to get the customer information and the product details from the input stream
	// 	void getdata() {
	// 	  Scanner sc = new Scanner(System.in);
	// 	  System.out.println("Enter the Customer Name: ");
	// 	  CustName = sc.nextLine();
	// 	  System.out.println("Enter the Customer Phone Number: ");
	// 	  CustPh = sc.next();
	// 	  System.out.println("Enter the Number of Products: ");
	// 	  n = sc.nextInt();
	// 	  System.out.println();
	// 	  for(int i=0; i<n; i++) {
	// 		System.out.println("Enter the Product Name: ");
	// 		ProductName.add(sc.next());
	// 		System.out.println("Enter the Price of the Product: ");
	// 		ProductPrice.add(sc.nextInt());
	// 		System.out.println("Enter the Quantity of the Product: ");
	// 		ProductQty.add(sc.nextInt());
	// 		System.out.println();
	// 		//Calculating the total amount
	// 		total = total + (ProductPrice.get(i)*ProductQty.get(i));
	// 	  }
	// 	}
		
	// 	void WriteInvoice() {
	// 	  //get the page
	// 	  PDPage mypage = invc.getPage(0);
	// 	  try {
	// 		//Prepare Content Stream
	// 		PDPageContentStream cs = new PDPageContentStream(invc, mypage);
			
	// 		//Writing Single Line text
	// 		//Writing the Invoice title
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 20);
	// 		cs.newLineAtOffset(140, 750);
	// 		cs.showText(InvoiceTitle);
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 18);
	// 		cs.newLineAtOffset(270, 690);
	// 		cs.showText(SubTitle);
	// 		cs.endText();
			
	// 		//Writing Multiple Lines
	// 		//writing the customer details
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(60, 610);
	// 		cs.showText("Customer Name: ");
	// 		cs.newLine();
	// 		cs.showText("Phone Number: ");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(170, 610);
	// 		cs.showText(CustName);
	// 		cs.newLine();
	// 		cs.showText(CustPh);
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.newLineAtOffset(80, 540);
	// 		cs.showText("Product Name");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.newLineAtOffset(200, 540);
	// 		cs.showText("Unit Price");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.newLineAtOffset(310, 540);
	// 		cs.showText("Quantity");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.newLineAtOffset(410, 540);
	// 		cs.showText("Price");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(80, 520);
	// 		for(int i =0; i<n; i++) {
	// 		  cs.showText(ProductName.get(i));
	// 		  cs.newLine();
	// 		}
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(200, 520);
	// 		for(int i =0; i<n; i++) {
	// 		  cs.showText(ProductPrice.get(i).toString());
	// 		  cs.newLine();
	// 		}
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(310, 520);
	// 		for(int i =0; i<n; i++) {
	// 		  cs.showText(ProductQty.get(i).toString());
	// 		  cs.newLine();
	// 		}
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	// 		cs.setLeading(20f);
	// 		cs.newLineAtOffset(410, 520);
	// 		for(int i =0; i<n; i++) {
	// 		  price = ProductPrice.get(i)*ProductQty.get(i);
	// 		  cs.showText(price.toString());
	// 		  cs.newLine();
	// 		}
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		cs.newLineAtOffset(310, (500-(20*n)));
	// 		cs.showText("Total: ");
	// 		cs.endText();
			
	// 		cs.beginText();
	// 		cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	// 		//Calculating where total is to be written using number of products
	// 		cs.newLineAtOffset(410, (500-(20*n)));
	// 		cs.showText(total.toString());
	// 		cs.endText();
			
	// 		//Close the content stream
	// 		cs.close();
	// 		//Save the PDF
	// 		invc.save(String FilePath);
			
	// 	  } catch (IOException e) {
	// 		e.printStackTrace();
	// 	  }
	// 	}
		
		
	// 	public static void main(String args[]) {
	// 	  invoice i = new invoice();
	// 	  i.getdata();
	// 	  i.WriteInvoice();
	// 	  System.out.println("Invoice Generated!");
	// 	}
	//   }
	
}


