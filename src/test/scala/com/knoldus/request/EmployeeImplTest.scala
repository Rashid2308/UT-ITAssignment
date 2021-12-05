package com.knoldus.request

import com.knoldus.models.Employee
import com.knoldus.validator.EmployeeValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.funsuite.AnyFunSuite

class EmployeeImplTest extends AnyFunSuite{
  val employeeValidator = mock[EmployeeValidator]
  val Rashid:Employee = new Employee("Rashid","Ali",22,15000,"Intern","Knoldus","rashid.ali@knoldus.com")
  val userImpl = new EmployeeImpl(employeeValidator)

  test("User can be ceated"){
    when(employeeValidator.employeeIsValid(Rashid)) thenReturn(true)
    val result = userImpl.createEmployee(Rashid)
    assert(result.isDefined)
  }
  test("User can not be created"){
    when(employeeValidator.employeeIsValid(Rashid)) thenReturn(false)
    val result = userImpl.createEmployee(Rashid)
    assert(result.isEmpty)
  }
}