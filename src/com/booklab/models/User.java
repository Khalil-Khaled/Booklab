/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.models;

/**
 *
 * @author radhw
 */
public abstract class User {
   
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
                private String userName;
                private String firstName;
		private String lastName;
		private String email;
		private String password;
		private String questionVerif;
		private String answerVerif;
		public User( String userName,String firstName, String lastName, String email, String password, String questionVerif,
				String answerVerif) {
			super();
			this.userName=userName;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.questionVerif = questionVerif;
			this.answerVerif = answerVerif;
		}
	
		public User() {
			
		}
	
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getQuestionVerif() {
			return questionVerif;
		}
		public void setQuestionVerif(String questionVerif) {
			this.questionVerif = questionVerif;
		}
		public String getAnswerVerif() {
			return answerVerif;
		}
		public void setAnswerVerif(String answerVerif) {
			this.answerVerif = answerVerif;
		}
		@Override
		public String toString() {
			return "User  userName=" + userName + ", firstName=" + firstName + ", lastName="
					+ lastName + ", email=" + email + ", password=" + password + ", questionVerif=" + questionVerif
					+ ", answerVerif=" + answerVerif + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((answerVerif == null) ? 0 : answerVerif.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((questionVerif == null) ? 0 : questionVerif.hashCode());
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (answerVerif == null) {
				if (other.answerVerif != null)
					return false;
			} else if (!answerVerif.equals(other.answerVerif))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (questionVerif == null) {
				if (other.questionVerif != null)
					return false;
			} else if (!questionVerif.equals(other.questionVerif))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			return true;
		}

}
