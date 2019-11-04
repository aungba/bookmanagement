import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '@app/login/login.component';
import { AuthGuard } from '@app/guard/auth.guard';

const routes: Routes = [
  {path: '', component: LoginComponent},
  // { path: '**', redirectTo: '/login', pathMatch: 'full' },
  // {path: 'book', loadChildren: '@app/book/book.module#BookModule'},

  // {path: 'user', loadChildren: '@app/user/user.module#UserModule'},
  {path: '#', loadChildren: () => import('@app/side-menu/side-menu.module').then(m => m.SideMenuModule), canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
