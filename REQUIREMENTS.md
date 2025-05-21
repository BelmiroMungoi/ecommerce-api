
# E-commerce System Requirements Document

## Project Overview
This system is a comprehensive e-commerce platform, built to support user interactions such as managing orders, handling payments, and user account management. The platform also incorporates seller functionalities for listing products and managing orders, as well as administrative features for overseeing system operations.

---

## Functional Requirements

### 1. **User Management**
- **Sign-Up and Authentication**: Users can register, log in, and log out securely.
- **Profile Management**: Users can update their personal information, such as name, email, password, and phone number.
- **Password Recovery**: Users can reset their password if forgotten, using a verified email.
- **Verification**: Users and sellers receive a verification code for account confirmation.

### 2. **Address Management**
- Users can add, edit, and remove multiple shipping addresses.
- Each order is linked to a specified address for accurate delivery.

### 3. **Cart Management**
- **Cart Creation**: Each user has a unique shopping cart to add, update, or remove items.
- **Cart Persistence**: Cart contents are retained even if the user logs out.

### 4. **Wishlist**
- Users can create a wishlist of products they may want to purchase in the future.
- **Wishlist Management**: Add or remove products in the wishlist, with limits on wishlist size.

### 5. **Product Management**
- **Category Management**: Products are organized into categories, supporting a nested structure with subcategories.
- **Product Details**: Each product has attributes such as price, description, availability status, and rating.
- **Stock Management**: For sellers to manage product inventory levels.

### 6. **Order Processing**
- **Order Creation**: Users can place orders, specifying shipping addresses and payment methods.
- **Order Status Tracking**: Users can track order progress from placement through delivery.
- **Cancellation & Returns**: Options to cancel or return orders based on conditions.

### 7. **Coupon System**
- Users can apply coupons to get discounts on purchases.
- Each coupon has constraints, such as expiration date, usage limits, and eligible products.

### 8. **Payments and Transactions**
- **Payment Options**: Multiple payment methods, including credit/debit cards and PayPal.
- **Order Payments**: Each order requires payment processing; users get a summary before confirming payment.
- **Transaction Records**: Detailed transaction logs, including payment status and related metadata.

### 9. **Reviews and Ratings**
- Users can review products and leave ratings.
- **Moderation**: Reviews are moderated to ensure quality and appropriateness.

### 10. **Seller Management**
- **Seller Profiles**: Sellers maintain profiles with business information and contact details.
- **Product Listing**: Sellers can add and manage product listings.
- **Sales Tracking**: Sellers can track orders and transactions.

### 11. **Reports and Analytics**
- **Sales Reports**: Sellers can generate reports on product performance.
- **Order Summaries**: Users and administrators can view summarized order histories and reports.
- **System Analytics**: Administrators can view site-wide sales metrics.

### 12. **Verification and Security**
- **User Verification**: New users verify their account via email.
- **Seller Verification**: Sellers go through additional verification for product listing permissions.
- **Role-Based Access Control**: Users, sellers, and administrators have defined roles with varying permissions.

---

## Non-Functional Requirements

### 1. **Scalability**
- The platform should be able to support a growing number of users, products, and transactions.

### 2. **Reliability**
- Ensure data consistency, especially for transactions and order processing.

### 3. **Security**
- Passwords are securely hashed, and sensitive data is encrypted.
- Protect against SQL injection, XSS, and other vulnerabilities.

### 4. **Performance**
- Optimize loading times for large product catalogs and ensure quick response times for search and filtering.

### 5. **Usability**
- User-friendly interface, optimized for various devices, with intuitive navigation and clear calls to action.

### 6. **Maintainability**
- Code is modular, well-documented, and adheres to standard coding conventions.

---

# Database Entities

## 1. User
- **Attributes**: `id`, `name`, `email`, `password`, `phoneNumber`, `role`, `createdAt`, `updatedAt`
- **Relationships**:
   - One-to-Many with **Address**, **Order**, **Transaction**, **Review**.
   - Many-to-Many with **Coupon**.
   - One-to-One with **Wishlist**.
   - One-to-One with **VerificationCode**.

## 2. Address
- **Attributes**: `id`, `street`, `city`, `state`, `postalCode`, `country`, `createdAt`
- **Relationships**:
   - Many-to-One with **User**, **Order**.
   - One-to-One with **Seller** (pickup address).

## 3. Cart
- **Attributes**: `id`, `createdAt`, `updatedAt`
- **Relationships**:
   - One-to-One with **User**.
   - One-to-Many with **CartItem**.

## 4. CartItem
- **Attributes**: `id`, `quantity`, `price`, `addedAt`
- **Relationships**:
   - Many-to-One with **Cart**.
   - Many-to-One with **Product**.

## 5. Product
- **Attributes**: `id`, `name`, `description`, `price`, `stock`, `createdAt`, `updatedAt`
- **Relationships**:
   - Many-to-One with **Category**, **Seller**.
   - One-to-Many with **Review**.
   - Many-to-Many with **Wishlist**.
   - Many-to-Many with **Coupon** (for discounts).

## 6. Category
- **Attributes**: `id`, `name`, `description`, `createdAt`
- **Relationships**:
   - Self-referential Many-to-One for parent category (to support subcategories).

## 7. Coupon
- **Attributes**: `id`, `code`, `discountPercentage`, `expiryDate`, `usageLimit`
- **Relationships**:
   - Many-to-Many with **User**.
   - Many-to-Many with **Product** (to limit coupon to specific products).

## 8. Order
- **Attributes**: `id`, `totalAmount`, `status`, `orderDate`, `shippingDate`, `deliveryDate`
- **Relationships**:
   - Many-to-One with **User**, **Address**.
   - One-to-Many with **OrderItem**.
   - One-to-One with **Transaction**.

## 9. OrderItem
- **Attributes**: `id`, `quantity`, `unitPrice`, `subtotal`
- **Relationships**:
   - Many-to-One with **Order**.
   - Many-to-One with **Product**.

## 10. PaymentOrder
- **Attributes**: `id`, `amount`, `status`, `createdAt`
- **Relationships**:
   - Many-to-One with **User**.
   - One-to-Many with **Order**.

## 11. Seller
- **Attributes**: `id`, `name`, `email`, `phoneNumber`, `createdAt`, `updatedAt`
- **Relationships**:
   - One-to-One with **Address**.
   - One-to-Many with **Product**.
   - One-to-Many with **Transaction**.
   - One-to-One with **VerificationCode**.
   - One-to-One with **SellerReport**.

## 12. Transaction
- **Attributes**: `id`, `amount`, `transactionDate`, `status`
- **Relationships**:
   - Many-to-One with **User**, **Seller**.
   - One-to-One with **Order**.

## 13. Review
- **Attributes**: `id`, `rating`, `comment`, `reviewDate`
- **Relationships**:
   - Many-to-One with **Product**.
   - Many-to-One with **User**.

## 14. Wishlist
- **Attributes**: `id`, `createdAt`, `updatedAt`
- **Relationships**:
   - One-to-One with **User**.
   - Many-to-Many with **Product**.

## 15. VerificationCode
- **Attributes**: `id`, `code`, `expiresAt`
- **Relationships**:
   - One-to-One with **User**.
   - One-to-One with **Seller**.

## 16. SellerReport
- **Attributes**: `id`, `reportDate`, `status`, `comments`
- **Relationships**:
   - One-to-One with **Seller**.


---

# API Endpoints

## 1. User Management
- `POST /api/users/register` - Register a new user
- `POST /api/users/login` - User login
- `POST /api/users/verify` - Verify user account
- `GET /api/users/profile` - Retrieve user profile information
- `PUT /api/users/profile` - Update user profile information
- `DELETE /api/users/delete` - Delete user account

## 2. Product Management
- `GET /api/products` - List all products with filters (category, price range, etc.)
- `GET /api/products/{id}` - Retrieve specific product details
- `POST /api/products` - Create new product (seller only)
- `PUT /api/products/{id}` - Update product details (seller only)
- `DELETE /api/products/{id}` - Delete product (seller only)
- `GET /api/products/{id}/reviews` - Get all reviews for a product
- `POST /api/products/{id}/reviews` - Add a review for a product (authenticated users only)

## 3. Category Management
- `GET /api/categories` - List all categories
- `POST /api/categories` - Create new category (admin only)
- `PUT /api/categories/{id}` - Update category details (admin only)
- `DELETE /api/categories/{id}` - Delete category (admin only)

## 4. Coupon Management
- `GET /api/coupons` - List all available coupons
- `POST /api/coupons` - Create new coupon (admin only)
- `PUT /api/coupons/{id}` - Update coupon details (admin only)
- `DELETE /api/coupons/{id}` - Delete coupon (admin only)
- `POST /api/coupons/apply` - Apply coupon to the cart (authenticated users only)

## 5. Cart Management
- `GET /api/cart` - Retrieve current user's cart details
- `POST /api/cart/items` - Add item to cart
- `PUT /api/cart/items/{id}` - Update item quantity in cart
- `DELETE /api/cart/items/{id}` - Remove item from cart
- `DELETE /api/cart/clear` - Clear all items in the cart

## 6. Wishlist Management
- `GET /api/wishlist` - Retrieve current user's wishlist
- `POST /api/wishlist/items` - Add item to wishlist
- `DELETE /api/wishlist/items/{id}` - Remove item from wishlist

## 7. Order Processing
- `POST /api/orders` - Place a new order
- `GET /api/orders/{id}` - View specific order details
- `GET /api/orders` - List all orders for the current user
- `PUT /api/orders/{id}/cancel` - Cancel an order (before shipment)

## 8. Address Management
- `GET /api/addresses` - List all addresses for current user
- `POST /api/addresses` - Add a new address
- `PUT /api/addresses/{id}` - Update an existing address
- `DELETE /api/addresses/{id}` - Remove an address

## 9. Transaction Management
- `GET /api/transactions` - Retrieve transaction history for the current user
- `POST /api/transactions/{orderId}/initiate` - Initiate a transaction for a specific order
- `GET /api/transactions/{id}` - View transaction details

## 10. Seller Management
- `POST /api/sellers/register` - Register a new seller account
- `GET /api/sellers/profile` - Retrieve seller profile information
- `PUT /api/sellers/profile` - Update seller profile information
- `GET /api/sellers/products` - List all products for the current seller
- `GET /api/sellers/transactions` - View transaction history for the seller

## 11. Review Management
- `GET /api/reviews` - List all reviews for a specific product
- `POST /api/reviews` - Submit a review for a product (authenticated users only)
- `PUT /api/reviews/{id}` - Update review (reviewer only)
- `DELETE /api/reviews/{id}` - Delete review (reviewer or admin only)

## 12. Verification and Authentication
- `POST /api/auth/verify-email` - Verify email address
- `POST /api/auth/reset-password` - Request password reset
- `POST /api/auth/update-password` - Update password after verification
- `GET /api/auth/validate-token` - Validate authentication token

## 13. Admin Dashboard
- `GET /api/admin/users` - View all users (admin only)
- `GET /api/admin/sellers` - View all sellers (admin only)
- `GET /api/admin/orders` - View all orders (admin only)
- `GET /api/admin/reports` - View system reports and analytics (admin only)


---

## Development Tools and Technology Stack
- **Frontend**: Angular, TypeScript, HTML, CSS
- **Backend**: Java, SpringBoot
- **Database**: PostgreSQL
- **Authentication**: JWT for stateless authentication
- **Payment Gateway Integration**: Stripe or PayPal, M-Pesa, E-mola, M-kesh

---

## Deployment and Hosting
- **Hosting**: AWS, Heroku, Render or DigitalOcean
- **CI/CD**: Github Actions or Jenkins for continuous integration and deployment
