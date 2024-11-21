import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {UserService} from '../services/user.service';
import {Router, RouterLink} from '@angular/router';
import {RegisterUser} from '../interfaces/Register-user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {


  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {

    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onRegister(): void {
    if (this.registerForm.valid) {
      const user: RegisterUser = this.registerForm.value;

      this.userService.registerUser(user).subscribe(
        (response:string) => {
          console.log('Registro exitoso:', response);
          this.router.navigate(['/login']);
        },

      );
    } else {
      console.log('Formulario inválido');

    }
  }


  get formControls() {
    return this.registerForm.controls;
  }


}
