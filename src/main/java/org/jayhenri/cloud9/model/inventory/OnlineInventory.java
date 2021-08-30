package org.jayhenri.cloud9.model.inventory;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.item.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Online inventory.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "online_inventory")
public class OnlineInventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column(name = "item_id", nullable = false)
    private UUID inventoryUUID;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @MapsId
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    /**
     * Instantiates a new Online inventory.
     *
     * @param item     the item
     * @param itemName the item name
     * @param quantity the quantity
     * @param price    the price
     */
    public OnlineInventory(Item item, String itemName, int quantity, double price) {
        this.item = item;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}
