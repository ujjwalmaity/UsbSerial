package dev.ujjwal.usbserial.model;

import android.hardware.usb.UsbDevice;

import com.hoho.android.usbserial.driver.UsbSerialDriver;

public class Device {
    UsbDevice device;
    int port;
    UsbSerialDriver driver;

    public Device(UsbDevice device, int port, UsbSerialDriver driver) {
        this.device = device;
        this.port = port;
        this.driver = driver;
    }

    public UsbDevice getDevice() {
        return device;
    }

    public int getPort() {
        return port;
    }

    public UsbSerialDriver getDriver() {
        return driver;
    }
}
