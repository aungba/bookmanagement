import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SideMenuComponent } from '@app/side-menu/side-menu.component';
import { AuthGuard } from '@app/guard/auth.guard';

const routes: Routes = [
  {path: '', component: SideMenuComponent,
  children: [
  {path: 'user', loadChildren: '@app/user/user.module#UserModule' ,canActivate: [AuthGuard]},
  {path: 'category', loadChildren: '@app/category/category.module#CategoryModule', canActivate: [AuthGuard]},
  {path: 'book', loadChildren: '@app/book/book.module#BookModule', canActivate: [AuthGuard]}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SideMenuRoutingModule { }
