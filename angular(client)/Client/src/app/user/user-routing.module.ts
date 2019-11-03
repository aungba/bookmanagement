import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from '@app/user/user.component';
import { UserListComponent } from '@app/user/user-list/user-list.component';

const routes: Routes = [
  {path: '', component: UserComponent,
    children: [
      { path: 'userlist', component: UserListComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
