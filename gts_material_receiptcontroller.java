package com.gts_qr.controller;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gts_qr.dao.gts_material_receiptdao;
import com.gts_qr.entity.gts_material_receipt;



@RestController
public class gts_material_receiptcontroller {
	@Autowired
    gts_material_receiptdao materialDAO;
	@PostMapping("gts_persistmaterialdetails")
	public ResponseEntity<?> gts_persistmaterialreceiptdetails(@RequestBody gts_material_receipt gtsmr)
	{	
		System.out.println("inside material receipt controller ()");
		
			
		try{
			materialDAO.persist_material_receipt_details(gtsmr);
			return new ResponseEntity<gts_material_receipt>(gtsmr,HttpStatus.OK);
		}
		catch(NoResultException e) {
			
			System.out.println("No result exception");
			return new ResponseEntity<gts_material_receipt>(gtsmr,HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
		
			System.out.println("null pointer exception");
			return new ResponseEntity<gts_material_receipt>(gtsmr,HttpStatus.OK);
		}
	}
	

}