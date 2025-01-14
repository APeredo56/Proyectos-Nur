import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor() { }

  dataURLToBlob(dataURL: string): Blob {
    const byteString = atob(dataURL.split(',')[1]);
    const mimeString = dataURL.split(',')[0].split(':')[1].split(';')[0];
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const uint8Array = new Uint8Array(arrayBuffer);
  
    for (let i = 0; i < byteString.length; i++) {
      uint8Array[i] = byteString.charCodeAt(i);
    }
  
    return new Blob([arrayBuffer], { type: mimeString });
  }

  private resizeImage(image: Blob, maxSizeInMB: number): Promise<Blob | null> {
    return new Promise((resolve) => {
      const reader = new FileReader();
      reader.onload = (e) => {
        const img = new Image();
        img.onload = () => {
          const canvas = document.createElement('canvas');
          const ctx = canvas.getContext('2d')!;
          // Tamaño máximo permitido en bytes
          const maxSizeInBytes = maxSizeInMB * 1024 * 1024;
          // Calcula nuevas dimensiones manteniendo la proporción original
          let width = img.width;
          let height = img.height;
          const aspectRatio = width / height;
          if (width > height) {
            width = Math.min(Math.sqrt(maxSizeInBytes * aspectRatio), img.width);
            height = (width / aspectRatio) | 0;
          } else {
            height = Math.min(Math.sqrt(maxSizeInBytes / aspectRatio), img.height);
            width = (height * aspectRatio) | 0;
          }
          canvas.width = width;
          canvas.height = height;
          // Redimensiona la imagen en el lienzo
          ctx.drawImage(img, 0, 0, width, height);
          canvas.toBlob((blob) => {
            resolve(blob);
          }, image.type);
        };
        img.src = e.target?.result as string;
      };
      reader.readAsDataURL(image);
    });
  }
  
  async compressImage(image: Blob) {
    const maxSizeInMB = 1; 
    return this.resizeImage(image, maxSizeInMB);
  }
}
