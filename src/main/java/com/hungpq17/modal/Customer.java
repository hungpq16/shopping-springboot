package com.hungpq17.modal;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

	private int customerId;

	private String name;

	private String email;

	private String password;

	private String phone;

	private Date registedDate;

	private short status;

}
