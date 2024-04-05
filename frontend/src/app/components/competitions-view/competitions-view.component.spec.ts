import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionsViewComponent } from './competitions-view.component';
import { CompetitionsViewModule } from './competitions-view.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CompetitionsViewComponent', () => {
  let component: CompetitionsViewComponent;
  let fixture: ComponentFixture<CompetitionsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompetitionsViewModule, HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(CompetitionsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
