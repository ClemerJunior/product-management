import {Component, OnInit} from '@angular/core';
import {CreateUpdateProductModel} from '../../models/create-update-product.model';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {ProductService} from '../../services/product.service';
import {CategoryModel} from '../../models/category.model';
import {MatFormField, MatError,MatLabel} from '@angular/material/form-field';
import {MatOption, MatSelect} from '@angular/material/select';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-product-insert',
  imports: [ReactiveFormsModule, MatFormField, MatSelect, MatOption, MatError,MatLabel,CommonModule],
  templateUrl: './product-insert.component.html',
  styleUrl: './product-insert.component.css',
  standalone: true
})
export class ProductInsertComponent implements OnInit {

  categories: CategoryModel[] = []

  form = new FormGroup({
    name: new FormControl("", [Validators.required]),
    description: new FormControl(""),
    price: new FormControl(0.00, [Validators.min(0)]),
    stock: new FormControl(0, [Validators.min(0)]),
    category: new FormControl(1, [Validators.min(1)])
  })

  constructor(private router: Router,
              private toastr: ToastrService,
              private route: ActivatedRoute,
              private productService: ProductService) {
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.productService.getCategories().subscribe(
      (data) => {
        // @ts-ignore
        this.categories = data; // Assign categories from the backend
      },
      (error) => {
        alert('Error loading categories.');
        console.error(error);
      }
    );
  }

  onSubmit() {
    if (this.form.valid) {
      const request: CreateUpdateProductModel = {
        name: this.form.value.name?? '',
        description: this.form.value.description?? '',
        price: this.form.value.price?? 0.00,
        stock: this.form.value.stock?? 0,
        idCategory: this.form.value.category?? 1
      }

      this.productService.insertProduct(request).subscribe(
        (response) => {
          this.toastr.success('Product added successfully!');
          this.router.navigate(['/admin/product']);
        },
        (error) => {
          this.toastr.error('Error adding product.');
          console.error(error);
        }
      );
    } else {
      alert('Please fill all required fields correctly.');
    }
  }

}
