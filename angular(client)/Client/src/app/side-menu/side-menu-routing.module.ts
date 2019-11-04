import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SideMenuComponent } from '@app/side-menu/side-menu.component';
import { AuthGuard } from '@app/guard/auth.guard';

const routes: Routes = [
  {path: '', component: SideMenuComponent,
  children: [
  {path: 'user', loadChildren: () => import('@app/user/user.module').then(m => m.UserModule) ,canActivate: [AuthGuard]},
  {path: 'category', loadChildren: () => import('@app/category/category.module').then(m => m.CategoryModule), canActivate: [AuthGuard]},
  {path: 'book', loadChildren: () => import('@app/book/book.module').then(m => m.BookModule), canActivate: [AuthGuard]}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SideMenuRoutingModule { }
