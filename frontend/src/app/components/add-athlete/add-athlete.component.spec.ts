import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAthleteComponent } from './add-athlete.component';
import { AddAthleteModule } from './add-athlete.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AddAthleteComponent', () => {
  let component: AddAthleteComponent;
  let fixture: ComponentFixture<AddAthleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAthleteModule,
      HttpClientTestingModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddAthleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
