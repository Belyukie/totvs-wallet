import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmRescueDialogComponent } from './confirm-rescue-dialog.component';

describe('ConfirmRescueDialogComponent', () => {
  let component: ConfirmRescueDialogComponent;
  let fixture: ComponentFixture<ConfirmRescueDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmRescueDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmRescueDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
