import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CompetitionRegistrationComponent } from './competition-registration.component';
import { CompetitionRegistrationModule } from './competition-registration.module';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CompetitionRegistrationComponent', () => {
  let component: CompetitionRegistrationComponent;
  let fixture: ComponentFixture<CompetitionRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompetitionRegistrationModule, HttpClientTestingModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({ someParam: 1 }),
            },
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(CompetitionRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
