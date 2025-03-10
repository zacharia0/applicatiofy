import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {User} from '../../Interfaces/IUser';
import {NgIf} from '@angular/common';
import {UpdateUser} from '../../Interfaces/IUpdateUser';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {Subject, takeUntil} from 'rxjs';
import Notiflix from 'notiflix';

@Component({
  selector: 'app-update-user',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent {

  destroy$ = new Subject<void>()
  updateForm: FormGroup
  userData: User | null
  message: string = ""

  constructor(private fb: FormBuilder, private authService: AuthService, private userService: UserService, private router: Router) {
    this.userData = this.authService.currentUserValue

    this.updateForm = this.fb.group({
      firstName: [this.userData?.firstName, [Validators.required,]],
      lastName: [this.userData?.lastName, [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(3)]],
      // password2: ['', [Validators.required, Validators.minLength(3)]]
    })
  }

  onUpdate(): void {
    if (this.updateForm.valid) {

      const updateData: UpdateUser = this.updateForm.value
      this.userService.update(updateData, this.userData?.username).pipe(takeUntil(this.destroy$)).subscribe({
        next: () => {
          console.log("Successfully Updated.")
          this.router.navigate(["/user-profile"])
          Notiflix.Notify.success("Successfully updated.")

        },
        error: (err) => {
          console.log(err)
          this.message = err.message
        }
      })

    }

  }

  onCancel(){
    this.router.navigate(["/user-profile"])

  }



}
