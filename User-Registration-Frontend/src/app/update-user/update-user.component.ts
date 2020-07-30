import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';
import { Error } from '../model/error'

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  public user: User = new User();
  successMessage: string;
  errorClient: Error = new Error();

  constructor(private route: ActivatedRoute,private router: Router,
    private userService: UserService) { 
     
    }

  ngOnInit() {
    //this.user = new User("","");

    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUser(this.id)
      .subscribe(
        data => {
        console.log(data)
        this.user = data;
        }, 
        error => { Error.errorMapper(this.errorClient,error); console.log(error);},
        () => {
          return console.log("loading");
        });;
  }

  updateUser(user:User) {
    this.userService.updateUser(this.id, user).
    subscribe(
      data => { this.user = data; this.successMessage =  "User ".concat(this.user.name).concat(" updated successfully"); }, 
      error => { Error.errorMapper(this.errorClient,error); console.log(error);},
      () => {
        return console.log("loading");
      });;
      this.user = new User();
      this.errorClient=new Error()
  }

  onSubmit(form:NgForm) {
    this.user.name = form.value.name;
    this.user.email = form.value.email;
    this.user.gender = form.value.gender;
    this.updateUser(this.user);    
  }
  
  gotoList() {
    this.router.navigate(['/users']);
  }

  userDetails(id: number){
    this.router.navigate(['details', id]);
  }

  gotoUserDetails() {
    this.router.navigate(['/details']);
  }
}
