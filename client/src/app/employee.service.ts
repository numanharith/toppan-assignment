import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { Employee } from "./employee";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class EmployeeService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    getEmployees(): Observable<Employee[]> {
        return this.http.get<Employee[]>(`${this.apiServerUrl}/employees/all`);
    }

    uploadCsv(file):Observable<any> {
  
        // Create form data
        const formData = new FormData(); 
        
        // Store form name as "file" with file data
        formData.append("file", file, file.name);
        
        // Make http post request over api
        // with formData as req
        return this.http.post(`${this.apiServerUrl}/employees/uploadcsv`, formData)
    }

    deleteEmployee(id: string): Observable<any> {
        return this.http.delete(`${this.apiServerUrl}/employees/delete/${id}`);
    }
}