import {CategoryModel} from './category.model';

export class ProductModel {
  id: number | undefined | null;
  name: string | undefined | null;
  description: string | undefined | null;
  price: number | undefined | null;
  stock: number | undefined | null;
  category: CategoryModel | undefined | null;
  available: boolean | undefined | null;
}
