import { Category } from "./category";

export class Book {
    book_id: number;
    title: string;
    author: string;
    publisher: string;
    summary: string;
    release_date: Date;
    status: boolean;
    isAvailable: string;
    category: Category = new Category();
    rent_date: Date;
}
