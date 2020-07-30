import { UserService } from '../user.service';
import { User } from '../model/user';
import { Component, OnInit, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, NgForm } from '@angular/forms';
import { Gender } from '../model/gender';
import { Error } from '../model/error'

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
@Injectable()
export class CreateUserComponent implements OnInit {

  public user: User = new User();
  successMessage: any;

  private gender = Gender;
  private genderOptions = [];
  public errorClient: Error = new Error();

  inscriptionFormulaire: FormGroup;

  constructor(private userService: UserService,
    private router: Router, private formBuilder: FormBuilder) {
      this.inscriptionFormulaire =  this.formBuilder.group({
        name:'',
        email: ''
      });
    }
    
    ngOnInit() {
      this.genderOptions = Object.keys(this.gender);
  }

  save(user:User) {
    
    this.userService.createUser(user).
    subscribe(
      data => { this.user = data; this.successMessage = "User created successfully";}, 
      error => { Error.errorMapper(this.errorClient,error); },
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
      this.save(this.user);    
      //this.gotoList();
  }

  userDetails(id: number){
    this.router.navigate(['details', this.user.id]);
  }

  gotoList() {
    this.router.navigate(['/users']);
  }
​​
}
