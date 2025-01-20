import {Component, OnInit} from '@angular/core';
import {CreateUpdateProductModel} from '../../models/create-update-product.model';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../services/product.service';
import {CategoryModel} from '../../models/category.model';
import {MatFormField, MatError, MatLabel} from '@angular/material/form-field';
import {MatOption, MatSelect} from '@angular/material/select';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-product-update',
  imports: [
    MatFormField,
    MatSelect,
    MatOption,
    MatError,
    MatLabel,
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './product-update.component.html',
  styleUrl: './product-update.component.css',
  standalone: true
})
export class ProductUpdateComponent implements OnInit {

  productId: number = 0;
  categories: CategoryModel[] = [];

  form = new FormGroup({
    name: new FormControl("", [Validators.required]),
    description: new FormControl(""),
    price: new FormControl(0.00, [Validators.min(0)]),
    stock: new FormControl(0, [Validators.min(0)]),
    category: new FormControl(null, [Validators.required])
  })

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.productId = params['id'];
      console.log(this.productId)
      this.loadProduct();
    });

    this.loadCategories();
  }

  loadProduct(): void {
    this.productService.getProduct(this.productId).subscribe(
      (product) => {
        // @ts-ignore
        this.form.patchValue(product);
      },
      (error) => {
        alert('Error loading product data.');
        console.error(error);
      }
    );
  }

  loadCategories(): void {
    this.productService.getCategories().subscribe(
      (data) => {
        // @ts-ignore
        this.categories = data;
      },
      (error) => {
        alert('Error loading categories.');
        console.error(error);
      }
    );
  }


    onSubmit(): void {
    if (this.form.valid) {
      const request: CreateUpdateProductModel = {
        name: this.form.value.name?? '',
        description: this.form.value.description?? '',
        price: this.form.value.price?? 0.00,
        stock: this.form.value.stock?? 0,
        idCategory: this.form.value.category?? 1
      }

      this.productService.updateProduct(this.productId,request).subscribe(
        (response) => {
          this.toastr.success('Product updated successfully!');
          this.router.navigate(['/admin/product']);
        },
        (error) => {
          this.toastr.error('Error updating product.');
          console.error(error);
        }
      );
    } else {
      alert('Please fill all required fields correctly.');
    }
  }
}
