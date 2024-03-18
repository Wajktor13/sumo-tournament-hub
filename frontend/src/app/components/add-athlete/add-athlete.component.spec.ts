import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAthleteComponent } from './add-athlete.component';
import { AddAthleteModule } from './add-athlere.module';

describe('AddAthleteComponent', () => {
  let component: AddAthleteComponent;
  let fixture: ComponentFixture<AddAthleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAthleteModule]
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
