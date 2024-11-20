export interface WorkPlace {
    country: string;
    name:String;
    readings:number;
    yellowAlerts: string;
    redAlerts:string
}

export interface CreateWorkPlaceRequest {
  country: string;
  name: string;
}
