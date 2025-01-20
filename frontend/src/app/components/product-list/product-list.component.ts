import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';
import {PageModel} from '../../models/page.model';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatOption} from '@angular/material/core';
import {MatSelect} from '@angular/material/select';
import {MatInput} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import {CategoryModel} from '../../models/category.model';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../security/authentication/authentication.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, CommonModule, MatButtonModule, MatPaginator, MatSort, MatIcon, MatLabel, MatFormField, MatOption, MatSelect, MatInput, FormsModule],
})
export class ProductListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'description', 'price', 'stock', 'category', 'categoryPath', 'available'];
  dataSource = new MatTableDataSource<PageModel>([]);
  totalProducts = 0;

  filters = {
    name: '',
    category: null,
    minPrice: null,
    maxPrice: null,
    available: false
  };

  categories: CategoryModel[] = [];

  constructor(private productService: ProductService,
              private router: Router,
              private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.loadProducts(0, 10, 'name,asc');
  }

  onFilterChange(): void {
    this.loadProducts(0, 10, 'name,asc');
  }

  loadProducts(page: number, size: number, sort: string): void {
    this.productService.getProducts(page, size, sort, this.getTransformedFilters()).subscribe((data) => {
      this.dataSource.data = data.items;
      this.totalProducts = data.totalElements;
    });

    this.loadCategories()
  }

  loadCategories(): void {
    this.productService.getCategories().subscribe(
      (data) => {
        // @ts-ignore
        this.categories = data;
        this.categories.push(new CategoryModel())
      },
      (error) => {
        alert('Error loading categories.');
        console.error(error);
      }
    );
  }

  manageProducts(): void {
    if(this.authService.isLoggedIn()){
      this.router.navigateByUrl("/admin/product")
    }
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  getTransformedFilters = (): Record<string, string> => Object.keys(this.filters).reduce((acc, key) => {
    // @ts-ignore
    const value = this.filters[key];
    if (value !== null && value !== undefined) {
      acc[key] = value.toString();
    }
    return acc;
  }, {} as Record<string, string>);
}
