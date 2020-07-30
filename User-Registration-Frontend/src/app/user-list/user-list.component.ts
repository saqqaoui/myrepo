import { UserDetailsComponent } from '../user-details/user-details.component';
import { Observable, of } from "rxjs";
import { UserService } from "./../user.service";
import { User } from "../model/user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { Gender } from '../model/gender';
import { Error } from '../model/error'

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"]
})
export class UserListComponent implements OnInit {
  public users: Observable<User[]>;
  public errorClient: Error = new Error();

  
  constructor(private userService: UserService,
    private router: Router) {
  }
    
  ngOnInit() {
      this.reloadData();
      this.errorClient= new Error();
      console.log(this.errorClient)
  }

  reloadData() {
   this.userService.getUsersList().
    subscribe(
      data => this.users = of(data), 
      error => { Error.errorMapper(this.errorClient,error); },
      () => {
        return console.log("loading");
      });
      this.errorClient= new Error();
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  userDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateUser(id: number){
    this.router.navigate(['update', id]);
  }

  getImageNumberFromGender(gender: Gender) {
    return gender === Gender.MALE ? 1 : 2;
  }
}
