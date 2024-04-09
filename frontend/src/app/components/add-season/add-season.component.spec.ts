import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSeasonComponent } from './add-season.component';
import { AddSeasonModule } from './add-season.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AddSeasonComponent', () => {
  let component: AddSeasonComponent;
  let fixture: ComponentFixture<AddSeasonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSeasonModule,
      HttpClientTestingModule]
    })
    .compileComponents();
    fixture = TestBed.createComponent(AddSeasonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
