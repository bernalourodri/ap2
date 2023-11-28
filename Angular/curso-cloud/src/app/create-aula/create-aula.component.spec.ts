import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAulaComponent } from './create-aula.component';

describe('CreateAulaComponent', () => {
  let component: CreateAulaComponent;
  let fixture: ComponentFixture<CreateAulaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateAulaComponent]
    });
    fixture = TestBed.createComponent(CreateAulaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
