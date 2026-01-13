class Producto:
    def __init__(self, referencia, nombre, precio, stock):
        self.referencia = referencia
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def vender(self, cantidad):
        # Lógica de venta y control de errores 
        if cantidad <= self.stock:
            self.stock -= cantidad
            print(f"Venta exitosa: Se han vendido {cantidad} unidades de {self.nombre}.")
        else:
            # Mensaje de error exacto solicitado 
            print(f"[ERROR] Stock insuficiente para la referencia {self.referencia}")

    def reponer(self, cantidad):
        # Lógica de reposición 
        self.stock += cantidad
        print(f"Reposición exitosa. Nuevo stock de {self.nombre}: {self.stock}")

# Bloque principal de prueba (Main) 
if __name__ == "__main__":
    # Instanciación de dos objetos 
    raton = Producto("REF001", "Ratón Gaming", 25.50, 10)
    teclado = Producto("REF002", "Teclado Mecánico", 40.00, 5)

    print("--- INICIO DE PRUEBAS ---")
    
    # 1. Venta exitosa 
    raton.vender(3)
    
    # 2. Intento de venta fallido (Error visible) 
    teclado.vender(10)
    
    # 3. Reposición de stock exitosa 
    teclado.reponer(10)
    
    # Prueba extra: venta exitosa tras reposición
    teclado.vender(10)
