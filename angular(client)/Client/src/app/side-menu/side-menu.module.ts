import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatSidenavModule, MatButtonModule } from '@angular/material';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { SideMenuRoutingModule } from './side-menu-routing.module';
import { SideMenuComponent } from './side-menu.component';

@NgModule({
  declarations: [SideMenuComponent],
  imports: [
    CommonModule,
    SideMenuRoutingModule,
    MatSidenavModule,
    MatButtonModule,
    AngularFontAwesomeModule
  ]
})
export class SideMenuModule { }
