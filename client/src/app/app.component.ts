import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {MatSort, Sort} from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public employees: Employee[] = [];
  public dataSource: MatTableDataSource<Employee> = new MatTableDataSource;


  displayedColumns: string[] = ['id', 'login', 'name', 'salary', 'actions'];

  constructor(
    private employeeService: EmployeeService, 
    private _liveAnnouncer: LiveAnnouncer
  ) {}

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  // ngAfterViewInit() {
  //   this.dataSource.sort = this.sort;
  // }

  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  ngOnInit(): void {
    this.employeeService.getEmployees().subscribe(response => { 
        this.employees = response
        this.dataSource = new MatTableDataSource<Employee>(this.employees);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      })
  }

  shortLink: string = "";
  loading: boolean = false; // Flag variable
  file: File = null; // Variable to store file

  onChange(event) {
    this.file = event.target.files[0];
  }

  onDelete(id: string) {
      this.employeeService.deleteEmployee(id).subscribe(
        (response) => {
          console.log(response);
        },
        (error: HttpErrorResponse) => {
          console.error("Error: ", error);
          alert(error.error);
        }
      )
  }

  onUpload() {
    this.employeeService.uploadCsv(this.file).subscribe(
        (response) => {
          console.log(response);
        },
        (error: HttpErrorResponse) => {
          console.error("Error: ", error);
          alert(error.error);
        }
    );
}
  // public getEmployees(): void {
  //   this.employeeService.getEmployees().subscribe(
  //     (response: Employee[]) => {
  //       console.log(response);
  //       this.employees = response;
  //     }, 
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   )
  // }
}
