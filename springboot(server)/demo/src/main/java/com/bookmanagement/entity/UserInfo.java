package com.bookmanagement.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userInfo")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 274337277752596815L;
	private int id;
	private String username;
    private String password;
    private UserRole role;
    private boolean gender;
    private UserAddress address;
    private String email;

//    public JwtRequest(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
