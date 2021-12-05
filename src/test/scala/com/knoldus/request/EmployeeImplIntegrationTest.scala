package com.knoldus.request

import com.knoldus.db.CompanyReadTo
import com.knoldus.models.Employee
import com.knoldus.validator.{EmailValidator, EmployeeValidator}
import org.scalatest.funsuite.AnyFunSuite


class EmployeeImplIntegrationTest extends AnyFunSuite {
  val companyName = new CompanyReadTo
  val validateEmail = new EmailValidator
  val employeeValidator = new EmployeeValidator(companyName, validateEmail)

  val employeeImpl = new EmployeeImpl(employeeValidator)

  test("User cannot be created because company does not exist"){
    val lucifer: Employee = new Employee("Lucifer", "MorningStar", 22, 10000, "Intern", "GoldMan Sachs", "lucifer.morningstar@gmalcom")
    val result = employeeImpl.createEmployee(lucifer)
    assert(result.isEmpty)
  }

  test("User not created because email is invalid"){
    val Rashid: Employee = new Employee("Rashid", "Ali", 22, 15000, "Intern", "Knoldus", "rashid.ali@gmailcom")
    val result = employeeImpl.createEmployee(Rashid)
    assert(result.isEmpty)
  }

  test("User cannot be created because email is invalid and company does not exist in DB"){
    val lucifer: Employee = new Employee("Lucifer", "MorningStar", 22, 10000, "Intern", "GoldMan Sachs", "lucifer.morningstar@gmalcom")
    val result = employeeImpl.createEmployee(lucifer)
    assert(result.isEmpty)
  }

  test("User can be created"){
    val Rashid: Employee = new Employee("Rashid", "Ali", 22, 15000, "Intern", "Knoldus", "rashidchoudhary732@gmail.com")
    val result = employeeImpl.createEmployee(Rashid)
    assert(result.isDefined)
  }

}
