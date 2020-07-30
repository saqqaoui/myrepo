import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { UserListComponent } from '../user-list/user-list.component';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { Error } from '../model/error'

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id: number;
  user: User;
  public errorClient: Error = new Error();

  
  constructor(private route: ActivatedRoute,private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.user = new User();

    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUser(this.id)
      .subscribe(
        data => this.user = data, 
        error => { Error.errorMapper(this.errorClient,error); },
        () => {
          return console.log("loading");
        });
        this.errorClient= new Error();
  }

  list(){
    this.router.navigate(['users']);
  }
}
